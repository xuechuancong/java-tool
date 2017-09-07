package org.jeff.javatool.tool.myactiviti.utils;

import org.activiti.bpmn.model.*;
import org.apache.commons.lang3.StringUtils;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.TNextTaskInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by weijianfu on 2016/11/29.
 */
public class FlowElementUtil {

    /**
     * 创建UserTask节点的自定义属性
     *
     * @param curRoleIds
     * @param nextTaskInfos
     * @return
     */
    public static List<CustomProperty> createCustomProperties(Collection<String> curRoleIds, Collection<TNextTaskInfo> nextTaskInfos
            , String canUpdateAtt, String canDownloadAtt, String canDeleteAtt, String canReadAtt, String canEditPage) {
        List<CustomProperty> customProperties = new ArrayList<>();

        if (curRoleIds != null && curRoleIds.size() > 0) {
            for (String curRoleId : curRoleIds) {
                addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CUR_ROLE_ID, curRoleId);
            }
        }

        if (nextTaskInfos != null && nextTaskInfos.size() > 0) {
            for (TNextTaskInfo nextTaskInfo : nextTaskInfos) {
                addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO, nextTaskInfo.getSimpleValue());
            }
        }

        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_ATT, canUpdateAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT, canDownloadAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DELETE_ATT, canDeleteAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_READ_ATT, canReadAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_EDIT_PAGE, canEditPage);

        //一下是新增属性，均有默认值
//        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CUR_ORGANIZATION_ID, organizationId);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPLOAD_ATT, WFConfig.TRUE_STRING);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT, WFConfig.FALSE_STRING);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT, WFConfig.FALSE_STRING);

        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_OPINION_TEXT, WFConfig.TRUE_STRING);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_ADD_SIGN, WFConfig.TRUE_STRING);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_DELEGATE, WFConfig.TRUE_STRING);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_APP_EDIT, WFConfig.FALSE_STRING);

        return customProperties;
    }

    /**
     * 创建UserTask节点的自定义属性
     *
     * @param curRoleIds
     * @param nextTaskInfos
     * @return
     */
    public static List<CustomProperty> createCustomProperties(Collection<String> curRoleIds
            , List<String> organizationIds
            , List<TNextTaskInfo> nextTaskInfos
            , String canUpdateAtt
            , String canDownloadAtt
            , String canDeleteAtt
            , String canReadAtt
            , String canEditPage
            , String canUploadAtt
            , String canUpdateOtherAtt
            , String canDeleteOtherAtt
            , String haveOpinionText
            , String haveAddSign
            , String haveDelegate
            , String canAppEdit) {

        List<CustomProperty> customProperties = new ArrayList<>();
        if (curRoleIds != null && curRoleIds.size() > 0) {
            for (String curRoleId : curRoleIds) {
                addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CUR_ROLE_ID, curRoleId);
            }
        }

        if (organizationIds != null && organizationIds.size() > 0) {
            for (String organizationId : organizationIds) {
                addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CUR_ORGANIZATION_ID, organizationId);
            }
        }

        if (nextTaskInfos != null && nextTaskInfos.size() > 0) {
            for (TNextTaskInfo nextTaskInfo : nextTaskInfos) {
                addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO, nextTaskInfo.getSimpleValue());
            }
        }

        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_ATT, canUpdateAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT, canDownloadAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DELETE_ATT, canDeleteAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_READ_ATT, canReadAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_EDIT_PAGE, canEditPage);

        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPLOAD_ATT, canUploadAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT, canUpdateOtherAtt);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT, canDeleteOtherAtt);

        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_OPINION_TEXT, haveOpinionText);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_ADD_SIGN, haveAddSign);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_HAVE_DELEGATE, haveDelegate);
        addCustomProperty(customProperties, WFConfig.CUSTOM_PROPERTY_CAN_APP_EDIT, canAppEdit);

        return customProperties;
    }

    private static void addCustomProperty(List<CustomProperty> customProperties, String name, String value) {
        CustomProperty canEditPageP = new CustomProperty();
        canEditPageP.setName(name);
        canEditPageP.setSimpleValue(value);
        customProperties.add(canEditPageP);
    }

    public static ServiceTask createServiceTask(String id, String name, String className) {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId(id);
        serviceTask.setName(name);
        serviceTask.setImplementation(className);
        serviceTask.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        return serviceTask;
    }

    public static UserTask createUserTask(String id, String name, List<CustomProperty> customProperties, TaskCategory taskCategory) {
        return createUserTask(id, name, null, customProperties, taskCategory);
    }

    /*任务节点*/
    public static UserTask createUserTask(String id, String name, List<String> candidateGroups, List<CustomProperty> customProperties, TaskCategory taskCategory) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        if (candidateGroups != null && candidateGroups.size() > 0) {
            userTask.setCandidateGroups(candidateGroups);
        }
        if (customProperties != null && customProperties.size() > 0) {
            userTask.setCustomProperties(customProperties);
        }
        //目前定义任务仅有常规任务、会签任务、并行审签
        if (TaskCategory.COUNTER_SIGN.equals(taskCategory) || TaskCategory.PARALLEL_SIGN.equals(taskCategory)) {//会签任务 并行审签任务
            MultiInstanceLoopCharacteristics loopCharacteristics = new MultiInstanceLoopCharacteristics();
            loopCharacteristics.setInputDataItem(WFConfig.V_COUNTER_SIGN_ACTIVITI_COLLECTION);
            loopCharacteristics.setCompletionCondition("${nrOfCompletedInstances == nrOfInstances}");//完成条件,[nrOfInstances实例总数][nrOfActiveInstances当前还没有完成的实例][nrOfCompletedInstances已经完成的实例个数]
            loopCharacteristics.setElementVariable(WFConfig.V_COUNTER_SIGN_ELEMENT_VARIABLE);
            loopCharacteristics.setSequential(false);//是否是顺序执行
            userTask.setLoopCharacteristics(loopCharacteristics);

            userTask.setCategory(taskCategory.getCategory());
        } else {//常规任务
            userTask.setCategory(TaskCategory.NORMAL.getCategory());
        }
        return userTask;
    }

    /*连线*/
    public static SequenceFlow createSequenceFlow(String from, String to, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        if (StringUtils.isNotEmpty(conditionExpression)) {
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }

    /*排他网关*/
    public static ExclusiveGateway createExclusiveGateway(String id) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        return exclusiveGateway;
    }

    /*并行网关*/
    public static ParallelGateway createParallelGateway(String id) {
        ParallelGateway parallelGateway = new ParallelGateway();
        parallelGateway.setId(id);
        return parallelGateway;
    }

    /*兼容网关*/
    public static InclusiveGateway createInclusiveGateway(String id) {
        InclusiveGateway inclusiveGateway = new InclusiveGateway();
        inclusiveGateway.setId(id);
        return inclusiveGateway;
    }

    /*开始节点*/
    public static StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        return startEvent;
    }

    /*结束节点*/
    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        return endEvent;
    }
}
