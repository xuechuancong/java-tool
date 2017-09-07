package org.jeff.javatool.tool.myactiviti;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作流-流程图片生成
 * Created by weijianfu on 2016/11/22.
 */
@Service
@Scope("singleton")
public class WFDiagramGenerator {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfigurationService;

    /**
     * @param processDefinitionId
     * @param executionId
     * @return
     * @throws Exception
     */
    public void generateImg(String processDefinitionId, String executionId, File file) throws Exception {
        InputStream imgInputStream = getImgInputStream(processDefinitionId, executionId);
        if (imgInputStream != null) {
            FileUtils.copyInputStreamToFile(imgInputStream, file);
        } else {
            throw new ActivitiException("没有满足生成条件的图片！");
        }
    }

    /**
     * 获取流程跟踪图片流
     *
     * @param processDefinitionId 流程定义ID
     * @param executionId         流程运行ID
     * @return
     * @throws Exception
     */
    public InputStream getImgInputStream(String processDefinitionId, String executionId) throws Exception {
        // 当前活动节点、活动线
        List<String> activeActivityIds = new ArrayList<>();
        List<String> highLightedFlows = new ArrayList<>();

        /**
         * 获得当前活动的节点
         */
        if (this.isFinished(executionId)) {// 如果流程已经结束，则得到结束节点
            activeActivityIds.add(historyService
                    .createHistoricActivityInstanceQuery()
                    .executionId(executionId).activityType("endEvent")
                    .singleResult().getActivityId());
        } else {// 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            activeActivityIds = runtimeService
                    .getActiveActivityIds(executionId);
        }
        /**
         * 获得当前活动的节点-结束
         */

        /**
         * 获得活动的线
         */
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().executionId(executionId)
                .orderByHistoricActivityInstanceStartTime().asc().list();
        // 计算活动线
        highLightedFlows = this.getHighLightedFlows(
                (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId)
                , historicActivityInstances);
        /**
         * 获得活动的线-结束
         */

        /**
         * 绘制图形
         */
        if (null != activeActivityIds) {
            InputStream imageStream = null;
            // 获得流程引擎配置
            ProcessEngineConfiguration processEngineConfiguration =
                    processEngineConfigurationService.getProcessEngineConfiguration();
            // 根据流程定义ID获得BpmnModel
            BpmnModel bpmnModel =
                    repositoryService.getBpmnModel(processDefinitionId);
            // 输出资源内容到相应对象
            imageStream = new DefaultProcessDiagramGenerator().generateDiagram(
                    bpmnModel
                    , "png"
                    , activeActivityIds
                    , highLightedFlows
                    , processEngineConfiguration.getActivityFontName()
                    , processEngineConfiguration.getLabelFontName()
                    , processEngineConfiguration.getAnnotationFontName()
                    , processEngineConfiguration.getClassLoader()
                    , 1.0);
            return imageStream;
        }
        return null;
    }

    /**
     * 流程是否已经结束
     *
     * @param processInstanceId 流程实例ID
     * @return
     */
    private boolean isFinished(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().finished()
                .processInstanceId(processInstanceId).count() > 0;
    }

    /**
     * 获得高亮线
     *
     * @param processDefinitionEntity   流程定义实体
     * @param historicActivityInstances 历史活动实体
     * @return 线ID集合
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size(); i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得 到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<>();// 用以保存后需开始时间相同的节点
            if ((i + 1) >= historicActivityInstances.size()) {
                break;
            }
            ActivityImpl sameActivityImpl1 = processDefinitionEntity// 将后面第一个节点放在时间相同节点的集合里
                    .findActivity(historicActivityInstances.get(i + 1).getActivityId());
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {// 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
