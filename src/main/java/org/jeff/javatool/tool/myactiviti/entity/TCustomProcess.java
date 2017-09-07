package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.activiti.engine.history.HistoricTaskInstance;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomJson;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 【自定义】流程定义主体
 * Created by weijianfu on 2017/2/28.
 */
public class TCustomProcess implements Serializable {

    /**
     * 流程定义Key，如果已有重复的KEY，则覆盖，为空则创建新定义
     */
    private String procDefKey;
    /**
     * 流程定义名称，not null
     */
    private String procDefName;
    /**
     * 流程描述
     */
    private String procDescription;
    /**
     * 流程定义类型
     */
    private String procDefCategory;
    /**
     * 挂在表单ID
     */
    private String formId;
    /**
     * 挂在表单名称
     */
    private String formName;
    /**
     * 挂在表单定义KEY
     */
    private String formDefKey;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 版本号，由后端生成
     */
    private Integer version;

    /**
     * 任务信息
     * <p>
     * key:任务定义KEY
     * value：任务主体
     */
    private Map<String, TCustomTask> tasks;

    /**
     * 流转信息
     * <p>
     * key:前端自定义流转ID
     * value：流转主体
     */
    private Map<String, TCustomFlow> flows;

    /**
     * 前端自定义的变量集，流程实例执行过程中会存入，在动态流程指引查询时才会填充该属性
     * <p>
     * key:任务定义KEY
     * value：变量集
     */
    private Map<String, Map<String, Object>> customVariable;

    public static TCustomProcess build(TProcCustom tProcCustom, TProcCustomJson tProcCustomJson) {
        TCustomProcess tCustomProcess = new TCustomProcess();

        tCustomProcess.setProcDefKey(tProcCustom.getProcDefKey());
        tCustomProcess.setProcDefName(tProcCustom.getProcDefName());
        tCustomProcess.setProcDescription(tProcCustom.getProcDescription());
        tCustomProcess.setProcDefCategory(tProcCustom.getProcDefCategory());
        tCustomProcess.setFormId(tProcCustom.getFormId());
        tCustomProcess.setFormName(tProcCustom.getFormName());
        tCustomProcess.setFormDefKey(tProcCustom.getFormDefKey());
        tCustomProcess.setCreator(tProcCustom.getCreator());
        tCustomProcess.setVersion(tProcCustom.getVersion());
        tCustomProcess.setFlows(tProcCustomJson.getFlows());
        tCustomProcess.setTasks(tProcCustomJson.getTasks());

        return tCustomProcess;
    }

    public Map<String, List<TCustomFlow>> getMapCurKey2Flows() {
        Map<String, List<TCustomFlow>> mapCurKey2Flows = Maps.newHashMap();
        if (flows == null || flows.isEmpty()) {
            return mapCurKey2Flows;
        }

        for (TCustomFlow tCustomFlow : this.flows.values()) {
            List<TCustomFlow> tCustomFlows = mapCurKey2Flows.get(tCustomFlow.getCurTaskDefKey());
            if (tCustomFlows == null) {
                tCustomFlows = Lists.newArrayList();
            }
            tCustomFlows.add(tCustomFlow);
            mapCurKey2Flows.put(tCustomFlow.getCurTaskDefKey(), tCustomFlows);
        }
        return mapCurKey2Flows;
    }

    public Map<String, List<TCustomFlow>> getMapTarKey2Flows() {
        Map<String, List<TCustomFlow>> mapTarKey2Flows = Maps.newHashMap();
        if (flows == null || flows.isEmpty()) {
            return mapTarKey2Flows;
        }

        for (TCustomFlow tCustomFlow : this.flows.values()) {
            List<TCustomFlow> tCustomFlows = mapTarKey2Flows.get(tCustomFlow.getTarTaskDefKey());
            if (tCustomFlows == null) {
                tCustomFlows = Lists.newArrayList();
            }
            tCustomFlows.add(tCustomFlow);
            mapTarKey2Flows.put(tCustomFlow.getTarTaskDefKey(), tCustomFlows);
        }
        return mapTarKey2Flows;
    }


    /**
     * 设置 TCustomFlow 中的 mark 字段值，供前端显示动态流程指引用
     *
     * @param hisTaskList 有序任务序列，从最初执行任务至最终执行任务
     * @param variableMap 流程实例的所有变量集
     */
    public void setFlowMark(List<HistoricTaskInstance> hisTaskList, Map<String, Object> variableMap) {
        if (hisTaskList == null || hisTaskList.size() <= 0
                || this.flows == null || this.flows.isEmpty()) {
            return;
        }
        Set<String> havePassedTaskDefKey = Sets.newHashSet();
        havePassedTaskDefKey.add(WFConfig.START_EVE_DEF_KEY);//如果已经有历史任务，说明开始节点必然存在
        boolean isProcEnd = true;

        for (HistoricTaskInstance historicTaskInstance : hisTaskList) {
            if (isProcEnd && historicTaskInstance.getEndTime() == null) {
                isProcEnd = false;
            }
            havePassedTaskDefKey.add(historicTaskInstance.getTaskDefinitionKey());
        }
        if (isProcEnd) {//所有的历史任务的EndTime都不为空，说明流程已经结束， 结束节点必然存在
            havePassedTaskDefKey.add(WFConfig.END_EVE_DEF_KEY);
        }

        //获取经过的任务定义KEY和对应的历史操作集合
        Map<String, List<String>> mapTaskKey2OperationList = getMapTaskKey2OperationList((String) variableMap.get(WFConfig.V_HIS_TASK_OPERATION));
        if (mapTaskKey2OperationList == null || mapTaskKey2OperationList.isEmpty()) {
            return;//说明没有历史任务
        }

        for (String curTaskDefKey : havePassedTaskDefKey) {
            List<String> operationList = mapTaskKey2OperationList.get(curTaskDefKey);
            if (CollectionUtils.isEmpty(operationList)) {
                continue;//没有历史任务或操作
            }
            for (String tarTaskDefKey : havePassedTaskDefKey) {//遍历所有的key，找出目标key
                if (curTaskDefKey != null && curTaskDefKey.equals(tarTaskDefKey)) {
                    continue;//一样则越过
                }
                for (String curOperation : operationList) {
                    String flowKey = TCustomFlow.getFlowKey(curTaskDefKey, tarTaskDefKey, curOperation);
                    TCustomFlow tCustomFlow = this.flows.get(flowKey);
                    if (tCustomFlow != null) {
                        tCustomFlow.setMark(true);//说明该流程实例之前经过了该流向
                    }
                }
            }
        }
    }

    private Map<String, List<String>> getMapTaskKey2OperationList(String hisFlowOperation) {
        Map<String, List<String>> mapTaskKey2OperationList = Maps.newHashMap();
        if (StringUtil.isBlank(hisFlowOperation)) {
            return mapTaskKey2OperationList;
        }
        List<String> taskKeyAndOperationList = StringUtil.split(hisFlowOperation, ",");
        for (String taskKeyAndOperation : taskKeyAndOperationList) {
            List<String> split = StringUtil.split(taskKeyAndOperation, "_");
            if (CollectionUtils.isEmpty(split)) {
                continue;
            }
            String taskKey = split.get(0);
            String operation = split.get(1);

            List<String> operationList = mapTaskKey2OperationList.get(taskKey);
            if (operationList == null) {
                operationList = Lists.newArrayList();
            }
            operationList.add(operation);
            mapTaskKey2OperationList.put(taskKey, operationList);
        }
        return mapTaskKey2OperationList;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getProcDefName() {
        return procDefName;
    }

    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    public String getProcDefCategory() {
        return procDefCategory;
    }

    public void setProcDefCategory(String procDefCategory) {
        this.procDefCategory = procDefCategory;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Map<String, TCustomTask> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, TCustomTask> tasks) {
        this.tasks = tasks;
    }

    public Map<String, TCustomFlow> getFlows() {
        return flows;
    }

    public void setFlows(Map<String, TCustomFlow> flows) {
        this.flows = flows;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProcDescription() {
        return procDescription;
    }

    public void setProcDescription(String procDescription) {
        this.procDescription = procDescription;
    }

    public Map<String, Map<String, Object>> getCustomVariable() {
        return customVariable;
    }

    public void setCustomVariable(Map<String, Map<String, Object>> customVariable) {
        this.customVariable = customVariable;
    }

    public String getFormDefKey() {
        return formDefKey;
    }

    public void setFormDefKey(String formDefKey) {
        this.formDefKey = formDefKey;
    }
}
