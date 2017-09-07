package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.utils.BpmnVariableUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 【自定义】任务定义主体
 * Created by weijianfu on 2017/2/28.
 */
public class TCustomTask implements Serializable {

    /**
     * 任务定义Key，not null
     * <p>
     * 开始任务固定为：startEvent
     * 结束任务固定为：endEvent
     */
    private String defKey;
    /**
     * 任务定义名称，not null
     */
    private String defName;
    /**
     * 任务定义类型，not null
     */
    private String defCategory;
    /**
     * 角色Id集合
     */
    private String roleIds;
    /**
     * 角色名称集合
     */
    private String roleNames;
    /**
     * 组织Id集合
     */
    private String organizationIds;
    /**
     * 组织名称集合
     */
    private String organizationNames;

    /**
     * 是否包含处理意见文本域，not null
     */
    private Boolean haveOpinionText;
    /**
     * 当前任务能否编辑表单，not null
     */
    private Boolean canEditPage;
    /**
     * 是否包含加签处理，not null
     */
    private Boolean haveAddSign;
    /**
     * 是否包含代理处理，not null
     */
    private Boolean haveDelegate;
    /**
     * 当前任务是否能显示附件，not null
     */
    private Boolean canReadAtt;
    /**
     * 当前任务能否下载附件，not null
     */
    private Boolean canDownloadAtt;
    /**
     * 当前任务能否上传附件，not null
     */
    private Boolean canUploadAtt;
    /**
     * 当前任务能否更新自己附件，not null
     */
    private Boolean canUpdateMyAtt;
    /**
     * 当前任务能否更新他人附件，not null
     */
    private Boolean canUpdateOtherAtt;
    /**
     * 当前任务能否删除自己附件，not null
     */
    private Boolean canDeleteMyAtt;
    /**
     * 当前任务能否删除他人附件，not null
     */
    private Boolean canDeleteOtherAtt;
    /**
     * 当前任务能否允许APP端操作，not null
     */
    private Boolean canAppEdit;

    /**
     * 任务超时标记时间
     */
    private Integer taskTimeout;

    /**
     * 调用的java类, 当defCategory为SERVICE类型时,必填
     */
    private String className;

    public List<String> getRoleIdsList() {
        List<String> roleIdsList = Lists.newArrayList();
        if (this.roleIds == null || this.roleIds.length() <= 0) {
            return roleIdsList;
        }
        return Lists.newArrayList(this.roleIds.split(","));
    }

    public List<String> getOrganizationIdsList() {
        List<String> organizationIdsList = Lists.newArrayList();
        if (this.organizationIds == null || this.organizationIds.length() <= 0) {
            return organizationIdsList;
        }
        return Lists.newArrayList(this.organizationIds.split(","));
    }

    public static TCustomTask buildByStartEvent() {
        TCustomTask tCustomTask = new TCustomTask();
        tCustomTask.setDefKey(WFConfig.START_EVE_DEF_KEY);
        return tCustomTask;
    }

    public static TCustomTask buildByEndEvent() {
        TCustomTask tCustomTask = new TCustomTask();
        tCustomTask.setDefKey(WFConfig.END_EVE_DEF_KEY);
        return tCustomTask;
    }

    public static TCustomTask buildByUserTask(UserTask userTask) {
        TCustomTask tCustomTask = new TCustomTask();
        tCustomTask.setDefKey(userTask.getId());
        tCustomTask.setDefName(userTask.getName());
        tCustomTask.setDefCategory(userTask.getCategory());

        Map<String, List<ExtensionElement>> extensionElementMap = userTask.getExtensionElements();

        tCustomTask.setRoleIds(StringUtil.concat(BpmnVariableUtil.codeRoleIds(extensionElementMap), ","));
        tCustomTask.setOrganizationIds(StringUtil.concat(BpmnVariableUtil.codeOrganizationIds(extensionElementMap), ","));
        tCustomTask.setCanEditPage(BpmnVariableUtil.codeCanEditPage(extensionElementMap));
        tCustomTask.setCanReadAtt(BpmnVariableUtil.codeCanReadAtt(extensionElementMap));
        tCustomTask.setCanDownloadAtt(BpmnVariableUtil.codeCanDownloadAtt(extensionElementMap));
        tCustomTask.setCanUploadAtt(BpmnVariableUtil.codeCanUploadAtt(extensionElementMap));
        tCustomTask.setCanUpdateMyAtt(BpmnVariableUtil.codeCanUpdateAtt(extensionElementMap));
        tCustomTask.setCanUpdateOtherAtt(BpmnVariableUtil.codeCanUpdateOtherAtt(extensionElementMap));
        tCustomTask.setCanDeleteMyAtt(BpmnVariableUtil.codeCanDeleteAtt(extensionElementMap));
        tCustomTask.setCanDeleteOtherAtt(BpmnVariableUtil.codeCanDeleteOtherAtt(extensionElementMap));
        tCustomTask.setHaveOpinionText(BpmnVariableUtil.codeHaveOpinionText(extensionElementMap));
        tCustomTask.setHaveAddSign(BpmnVariableUtil.codeHaveAddSign(extensionElementMap));
        tCustomTask.setHaveDelegate(BpmnVariableUtil.codeHaveDelegate(extensionElementMap));
        tCustomTask.setCanAppEdit(BpmnVariableUtil.codeCanAppEdit(extensionElementMap));

        return tCustomTask;
    }

    public static List<TNextTaskInfo> buildTNextTaskInfoByUserTask(FlowElement flowElement) {
        List<TNextTaskInfo> tNextTaskInfoList = Lists.newArrayList();

        Map<String, List<ExtensionElement>> extensionElementMap = flowElement.getExtensionElements();

        List<ExtensionElement> nextTaskInfosEE = extensionElementMap.get(WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO);
        Collection<String> nextTaskInfos = Lists.newArrayList();

        if (nextTaskInfosEE != null && nextTaskInfosEE.size() > 0) {
            for (ExtensionElement extensionElement : nextTaskInfosEE) {
                nextTaskInfos.add(extensionElement.getElementText());
            }
        }

        if (nextTaskInfos == null || nextTaskInfos.size() <= 0) {
            return tNextTaskInfoList;
        }
        for (String string : nextTaskInfos) {
            tNextTaskInfoList.add(TNextTaskInfo.parse(string));
        }
        return tNextTaskInfoList;
    }


    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefCategory() {
        return defCategory;
    }

    public void setDefCategory(String defCategory) {
        this.defCategory = defCategory;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(String organizationIds) {
        this.organizationIds = organizationIds;
    }

    public Boolean getHaveOpinionText() {
        return haveOpinionText;
    }

    public void setHaveOpinionText(Boolean haveOpinionText) {
        this.haveOpinionText = haveOpinionText;
    }

    public Boolean getCanEditPage() {
        return canEditPage;
    }

    public void setCanEditPage(Boolean canEditPage) {
        this.canEditPage = canEditPage;
    }

    public Boolean getHaveAddSign() {
        return haveAddSign;
    }

    public void setHaveAddSign(Boolean haveAddSign) {
        this.haveAddSign = haveAddSign;
    }

    public Boolean getHaveDelegate() {
        return haveDelegate;
    }

    public void setHaveDelegate(Boolean haveDelegate) {
        this.haveDelegate = haveDelegate;
    }

    public Boolean getCanReadAtt() {
        return canReadAtt;
    }

    public void setCanReadAtt(Boolean canReadAtt) {
        this.canReadAtt = canReadAtt;
    }

    public Boolean getCanDownloadAtt() {
        return canDownloadAtt;
    }

    public void setCanDownloadAtt(Boolean canDownloadAtt) {
        this.canDownloadAtt = canDownloadAtt;
    }

    public Boolean getCanUploadAtt() {
        return canUploadAtt;
    }

    public void setCanUploadAtt(Boolean canUploadAtt) {
        this.canUploadAtt = canUploadAtt;
    }

    public Boolean getCanUpdateMyAtt() {
        return canUpdateMyAtt;
    }

    public void setCanUpdateMyAtt(Boolean canUpdateMyAtt) {
        this.canUpdateMyAtt = canUpdateMyAtt;
    }

    public Boolean getCanUpdateOtherAtt() {
        return canUpdateOtherAtt;
    }

    public void setCanUpdateOtherAtt(Boolean canUpdateOtherAtt) {
        this.canUpdateOtherAtt = canUpdateOtherAtt;
    }

    public Boolean getCanDeleteMyAtt() {
        return canDeleteMyAtt;
    }

    public void setCanDeleteMyAtt(Boolean canDeleteMyAtt) {
        this.canDeleteMyAtt = canDeleteMyAtt;
    }

    public Boolean getCanDeleteOtherAtt() {
        return canDeleteOtherAtt;
    }

    public void setCanDeleteOtherAtt(Boolean canDeleteOtherAtt) {
        this.canDeleteOtherAtt = canDeleteOtherAtt;
    }

    public Boolean getCanAppEdit() {
        return canAppEdit;
    }

    public void setCanAppEdit(Boolean canAppEdit) {
        this.canAppEdit = canAppEdit;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getOrganizationNames() {
        return organizationNames;
    }

    public void setOrganizationNames(String organizationNames) {
        this.organizationNames = organizationNames;
    }

    public Integer getTaskTimeout() {
        return taskTimeout;
    }

    public void setTaskTimeout(Integer taskTimeout) {
        this.taskTimeout = taskTimeout;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
