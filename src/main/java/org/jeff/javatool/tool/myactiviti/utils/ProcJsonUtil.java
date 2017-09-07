package org.jeff.javatool.tool.myactiviti.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.TCustomFlow;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.TCustomTask;
import org.jeff.javatool.tool.myactiviti.entity.TNextTaskInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 流程实体与json（前后端传输用）转换工具类
 * Created by weijianfu on 2017/4/10.
 */
public class ProcJsonUtil {


    /**
     * 通过【流程定义实体】获取【前后端传输对象】
     *
     * @param processList
     * @return
     */
    public static TCustomProcess getTCustomProcess(List<Process> processList) throws Exception {
        if (processList == null || processList.size() <= 0) {
            throw new Exception("processList中没有元素");
        } else if (processList.size() > 1) {
            throw new Exception("processList中元素数量大于1");
        }
        TCustomProcess tCustomProcess = new TCustomProcess();
        Process process = processList.get(0);
        tCustomProcess.setProcDefKey(process.getId());
        tCustomProcess.setProcDefName(process.getName());
//        tCustomProcess.setProcDefCategory();

        Collection<FlowElement> flowElementColl = process.getFlowElements();
        if (flowElementColl == null || flowElementColl.size() <= 0) {
            throw new Exception("flowElementColl中没有元素");
        }
        Map<String, TCustomTask> tasks = Maps.newHashMap();
        List<String> gatewayIdList = Lists.newArrayList();//所有网关ID集合
        Map<String, List<SequenceFlow>> curKey2FlowListMap = Maps.newHashMap();//所有流向，key为起始任务KEY
        Map<String, List<TNextTaskInfo>> curKey2NextInfoListMap = Maps.newHashMap();//所有下一步信息，key为起始任务KEY或网关ID
        for (FlowElement flowElement : flowElementColl) {
            if (flowElement instanceof UserTask) {//任务
                UserTask userTask = (UserTask) flowElement;
                TCustomTask tCustomTask = TCustomTask.buildByUserTask(userTask);
                tasks.put(tCustomTask.getDefKey(), tCustomTask);
                curKey2NextInfoListMap.put(userTask.getId(), TCustomTask.buildTNextTaskInfoByUserTask(userTask));
            } else if (flowElement instanceof StartEvent) {//开始节点
                TCustomTask tCustomTask = TCustomTask.buildByStartEvent();
                tasks.put(tCustomTask.getDefKey(), tCustomTask);
                curKey2NextInfoListMap.put(tCustomTask.getDefKey(), null);
            } else if (flowElement instanceof EndEvent) {//结束节点
                TCustomTask tCustomTask = TCustomTask.buildByEndEvent();
                tasks.put(tCustomTask.getDefKey(), tCustomTask);
                curKey2NextInfoListMap.put(tCustomTask.getDefKey(), null);
            } else if (flowElement instanceof Gateway) {//网关
                gatewayIdList.add(flowElement.getId());
            } else if (flowElement instanceof SequenceFlow) {//流向
                SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
                List<SequenceFlow> sequenceFlowList = curKey2FlowListMap.get(sequenceFlow.getSourceRef());
                if (sequenceFlowList == null) {
                    sequenceFlowList = Lists.newArrayList();
                }
                sequenceFlowList.add(sequenceFlow);
                curKey2FlowListMap.put(sequenceFlow.getSourceRef(), sequenceFlowList);
            }
        }
        //生成Flows
        Map<String, TCustomFlow> flows = buildFlows(gatewayIdList, curKey2NextInfoListMap, curKey2FlowListMap);

        tCustomProcess.setTasks(tasks);
        tCustomProcess.setFlows(flows);
        return tCustomProcess;
    }

    /**
     * 通过【前后端传输对象】获取【流程定义实体】
     *
     * @param tCustomProcess
     * @return
     * @throws Exception
     */
    public static List<Process> getProcessList(TCustomProcess tCustomProcess) throws Exception {
        List<Process> processColl = new ArrayList<>();
        Process process = new Process();
        process.setId(tCustomProcess.getProcDefKey());
        process.setName(tCustomProcess.getProcDefName());

        Map<String, List<TCustomFlow>> mapCurKey2Flows = tCustomProcess.getMapCurKey2Flows();//当前任务的去流向
        Map<String, List<TCustomFlow>> mapTarKey2Flows = tCustomProcess.getMapTarKey2Flows();//当前任务的来流向
        Map<String, TCustomTask> mapDefKey2Task = tCustomProcess.getTasks();

        if(mapDefKey2Task == null || mapDefKey2Task.isEmpty()
                || mapCurKey2Flows == null || mapCurKey2Flows.isEmpty()
                || mapTarKey2Flows == null || mapTarKey2Flows.isEmpty()){
            throw new Exception("流程定义参数不全，请检查tasks、flows等");
        }
        Integer gatewayIndex = 0;

        //查找多来流向的任务，在其前面加上一个Gateway，用于汇总所有来流向
//        Map<String, String> mapTaskDefKey2GatewayId = Maps.newHashMap();
//        for (String taskDefKey : mapTarKey2Flows.keySet()) {
//            List<TCustomFlow> tCustomFlows = mapTarKey2Flows.get(taskDefKey);
//            if (tCustomFlows != null && tCustomFlows.size() > 1) {
//                String gatewayId = "createInclusiveGateway" + ++gatewayIndex;
//                process.addFlowElement(FlowElementUtil.createInclusiveGateway(gatewayId));//新增一个网关
//                process.addFlowElement(FlowElementUtil.createSequenceFlow(gatewayId, taskDefKey, "", ""));//新增该网关到该节点的流向
//
//                mapTaskDefKey2GatewayId.put(taskDefKey, gatewayId);
//            }
//        }

        for (TCustomTask tCustomTask : mapDefKey2Task.values()) {
            //解析出任务定义
            parseTasks(process, tCustomTask, mapDefKey2Task, mapCurKey2Flows);

            //解析出流向定义
            gatewayIndex = parseFlows(process, tCustomTask, mapCurKey2Flows, mapTarKey2Flows, gatewayIndex);
        }
        processColl.add(process);
        return processColl;
    }

    private static int parseFlows(Process process, TCustomTask tCustomTask
            , Map<String, List<TCustomFlow>> mapCurKey2Flows
            , Map<String, List<TCustomFlow>> mapTarKey2Flows, Integer gatewayIndex) throws Exception {

        int gatewayIndexTemp = gatewayIndex;

        List<TCustomFlow> tCustomFlowListCur = mapCurKey2Flows.get(tCustomTask.getDefKey());//流出
        List<TCustomFlow> tCustomFlowListTar = mapTarKey2Flows.get(tCustomTask.getDefKey());//流入
        if ((tCustomFlowListCur == null || tCustomFlowListCur.size() <= 0)
                && (tCustomFlowListTar == null || tCustomFlowListTar.size() <= 0)) {
            throw new Exception("任务定义KEY为【" + tCustomTask.getDefKey() + "】的来流向、去流向总数量为0！");
        } else if (tCustomFlowListCur!= null && tCustomFlowListCur.size() == 1) {//单流向，无需添加网关
            TCustomFlow tCustomFlow = tCustomFlowListCur.get(0);
//            String realKey = getKey(mapTaskDefKey2GatewayId, tCustomFlow.getTarTaskDefKey());
            process.addFlowElement(FlowElementUtil.createSequenceFlow(tCustomFlow.getCurTaskDefKey(), tCustomFlow.getTarTaskDefKey(), "", ""));
        } else if(tCustomFlowListCur!= null && tCustomFlowListCur.size() > 1){//多流向，需添加网关、条件
            String gatewayId = "createExclusiveGateway" + ++gatewayIndexTemp;
            process.addFlowElement(FlowElementUtil.createExclusiveGateway(gatewayId));
            process.addFlowElement(FlowElementUtil.createSequenceFlow(tCustomTask.getDefKey(), gatewayId, "", ""));

            for (TCustomFlow tCustomFlow : tCustomFlowListCur) {
//                String realKey = getKey(mapTaskDefKey2GatewayId, tCustomFlow.getTarTaskDefKey());
                process.addFlowElement(FlowElementUtil.createSequenceFlow(gatewayId, tCustomFlow.getTarTaskDefKey(), "", tCustomFlow.getExpression()));
            }
        }

        return gatewayIndexTemp;
    }

    private static void parseTasks(Process process, TCustomTask tCustomTask
            , Map<String, TCustomTask> mapDefKey2Task
            , Map<String, List<TCustomFlow>> mapCurKey2Flows) throws Exception {
        if(TaskCategory.SERVICE.getCategory().equals(tCustomTask.getDefCategory())){
            process.addFlowElement(FlowElementUtil.createServiceTask(tCustomTask.getDefKey(), tCustomTask.getDefName(), tCustomTask.getClassName()));
        }else if (WFConfig.START_EVE_DEF_KEY.equals(tCustomTask.getDefKey())) {
            process.addFlowElement(FlowElementUtil.createStartEvent());
        } else if (WFConfig.END_EVE_DEF_KEY.equals(tCustomTask.getDefKey())) {
            process.addFlowElement(FlowElementUtil.createEndEvent());
        } else {
            List<CustomProperty> customProperties =
                    FlowElementUtil.createCustomProperties(tCustomTask.getRoleIdsList()
                            , tCustomTask.getOrganizationIdsList()
                            , getNextTaskInfos(tCustomTask, mapDefKey2Task, mapCurKey2Flows.get(tCustomTask.getDefKey()))
                            , tCustomTask.getCanUpdateMyAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanDownloadAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanDeleteMyAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanReadAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanEditPage() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanUploadAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanUpdateOtherAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanDeleteOtherAtt() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getHaveOpinionText() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getHaveAddSign() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getHaveDelegate() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING
                            , tCustomTask.getCanAppEdit() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING);
            FlowElement userTask = FlowElementUtil.createUserTask(tCustomTask.getDefKey()
                    , tCustomTask.getDefName()
                    , customProperties
                    , TaskCategory.getEntity(tCustomTask.getDefCategory()));
            process.addFlowElement(userTask);
        }
    }

    private static String getKey(Map<String, String> mapTaskDefKey2GatewayId, String tarTaskDefKey) {
        if (mapTaskDefKey2GatewayId == null || mapTaskDefKey2GatewayId.isEmpty() || tarTaskDefKey == null) {
            return tarTaskDefKey;
        }
        String gatewayId = mapTaskDefKey2GatewayId.get(tarTaskDefKey);
        if (gatewayId != null && gatewayId.length() > 0) {
            return gatewayId;
        }

        return tarTaskDefKey;
    }

    private static List<TNextTaskInfo> getNextTaskInfos(TCustomTask tCustomTask
            , Map<String, TCustomTask> mapDefKey2Task
            , List<TCustomFlow> customFlowList) throws Exception {
        if (customFlowList == null || customFlowList.size() <= 0) {
            throw new Exception("任务定义KEY为【" + tCustomTask.getDefKey() + "】的流向数量为0！");
        }
        List<TNextTaskInfo> nextTaskInfoList = Lists.newArrayList();
        for (TCustomFlow tCustomFlow : customFlowList) {
            TCustomTask tarCustomTask = mapDefKey2Task.get(tCustomFlow.getTarTaskDefKey());//目标任务

            TNextTaskInfo tNextTaskInfo = new TNextTaskInfo();
            tNextTaskInfo.setTaskDefinitionKey(tarCustomTask.getDefKey());
            tNextTaskInfo.setTaskName(tarCustomTask.getDefName());
            tNextTaskInfo.setRoleIdList(tarCustomTask.getRoleIdsList());
            tNextTaskInfo.setOrganizationIdList(tarCustomTask.getOrganizationIdsList());
            tNextTaskInfo.setOperation(tCustomFlow.getFlowType());
            tNextTaskInfo.setOperationName(tCustomFlow.getFlowName());
            tNextTaskInfo.setCanSendMsg(tCustomFlow.getCanSendMsg());
            tNextTaskInfo.setCanSendOSMsg(tCustomFlow.getCanSendOSMsg());
            tNextTaskInfo.setTaskCategory(tarCustomTask.getDefCategory());
            tNextTaskInfo.setAbnormalProc(tCustomFlow.getAbnormalProc());

            nextTaskInfoList.add(tNextTaskInfo);
        }

        return nextTaskInfoList;
    }

    /**
     * @param gatewayIdList          所有网关ID集合
     * @param curKey2NextInfoListMap 所有下一步信息，key为起始任务KEY
     * @param curKey2FlowListMap     所有流向，key为起始任务KEY或网关ID
     * @return
     */
    private static Map<String, TCustomFlow> buildFlows(List<String> gatewayIdList
            , Map<String, List<TNextTaskInfo>> curKey2NextInfoListMap
            , Map<String, List<SequenceFlow>> curKey2FlowListMap) {
        Map<String, TCustomFlow> flows = Maps.newHashMap();

        //获取网关对应的下游任务
        Map<String, List<String>> gatewayId2TaskKeyList = Maps.newHashMap();
        if (gatewayIdList != null && gatewayIdList.size() > 0) {
            for (String gatewayId : gatewayIdList) {
                List<SequenceFlow> sequenceFlows = curKey2FlowListMap.get(gatewayId);
                gatewayId2TaskKeyList.put(gatewayId, getTarKeyList(sequenceFlows));
            }
        }
        //拼装flows
        for (String taskKey : curKey2NextInfoListMap.keySet()) {//所有任务定义Key遍历，不包含网关ID
            List<TNextTaskInfo> tNextTaskInfos = curKey2NextInfoListMap.get(taskKey);
            List<SequenceFlow> sequenceFlows = curKey2FlowListMap.get(taskKey);
            if (sequenceFlows == null || sequenceFlows.size() <= 0) {//有可能是结束任务
                continue;
            }
            List<TCustomFlow> curTCustomFlowList = Lists.newArrayList();//当前任务为起点的所有流向
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                List<String> tarTaskKeyList = gatewayId2TaskKeyList.get(sequenceFlow.getTargetRef());
                if (tarTaskKeyList != null && tarTaskKeyList.size() > 0) {//说明当前的目标点为网关，所以需要替换为任务
                    for (String tarTaskKey : tarTaskKeyList) {
                        TCustomFlow tCustomFlow = new TCustomFlow();
                        tCustomFlow.setCurTaskDefKey(sequenceFlow.getSourceRef());
                        tCustomFlow.setExpression(sequenceFlow.getConditionExpression());
                        tCustomFlow.setTarTaskDefKey(tarTaskKey);
                        curTCustomFlowList.add(tCustomFlow);
                    }
                    continue;
                }
                TCustomFlow tCustomFlow = new TCustomFlow();
                tCustomFlow.setCurTaskDefKey(sequenceFlow.getSourceRef());
                tCustomFlow.setExpression(sequenceFlow.getConditionExpression());
                tCustomFlow.setTarTaskDefKey(sequenceFlow.getTargetRef());//说明当前的目标点为任务
                curTCustomFlowList.add(tCustomFlow);
            }


            //填充其余参数
            for (TCustomFlow tCustomFlow : curTCustomFlowList) {
                if(WFConfig.START_EVE_DEF_KEY.equals(tCustomFlow.getCurTaskDefKey())){
                    flows.put(TCustomFlow.getFlowKey(tCustomFlow.getCurTaskDefKey(), tCustomFlow.getTarTaskDefKey(), ""), tCustomFlow);
                    continue;
                }

                if (tNextTaskInfos == null || tNextTaskInfos.size() <= 0) {
                    continue;
                }

                for (TNextTaskInfo tNextTaskInfo : tNextTaskInfos) {
                    if (tNextTaskInfo.getTaskDefinitionKey() != null//NextTaskInfo与tCustomFlow匹配后，填充数据
                            && tNextTaskInfo.getTaskDefinitionKey().equals(tCustomFlow.getTarTaskDefKey())) {
                        tCustomFlow.setFlowName(tNextTaskInfo.getOperationName());
                        tCustomFlow.setFlowType(tNextTaskInfo.getOperation());
                        tCustomFlow.setCanSendMsg(tNextTaskInfo.isCanSendMsg());
                        tCustomFlow.setCanSendOSMsg(tNextTaskInfo.isCanSendOSMsg());

                        flows.put(TCustomFlow.getFlowKey(tCustomFlow.getCurTaskDefKey(), tCustomFlow.getTarTaskDefKey(), tNextTaskInfo.getOperation()), tCustomFlow);
                        break;
                    }
                }
            }

        }
        return flows;
    }

    private static List<String> getTarKeyList(List<SequenceFlow> sequenceFlows) {
        List<String> tarKeyList = Lists.newArrayList();
        if (sequenceFlows == null || sequenceFlows.size() <= 0) {
            return tarKeyList;
        }
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            tarKeyList.add(sequenceFlow.getTargetRef());
        }
        return tarKeyList;
    }


}
