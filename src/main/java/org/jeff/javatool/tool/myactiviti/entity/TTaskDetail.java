package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.utils.BpmnVariableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 详情传输对象
 * 任务
 * Created by weijianfu on 2016/11/30.
 */
public class TTaskDetail implements Serializable {
    private Task task;
    private String processDefinitionKey;
    /**
     * 角色Id集合 *
     */
    private Collection<String> curRoleIds;
    /**
     * 组织Id集合 *
     */
    private Collection<String> curOrganizationIds;
    /**
     * 所有下一步可达的任务集合 *
     */
    private Collection<TNextTaskInfo> nextTaskInfos;
    /**
     * 流程实例变量 *
     */
    private Map<String, Object> variables;

    /**
     * 是否能编辑本任务表单 *
     */
    private boolean canEditPage;
    /**
     * 是否能修改自己附件 *
     */
    private boolean canUpdateAtt;
    /**
     * 是否能下载附件 *
     */
    private boolean canDownloadAtt;
    /**
     * 是否能删除自己附件 *
     */
    private boolean canDeleteAtt;
    /**
     * 是否能显示附件 *
     */
    private boolean canReadAtt;
    /**
     * 是否能下载附件 *
     */
    private boolean canUploadAtt;
    /**
     * 是否能修改别人附件 *
     */
    private boolean canUpdateOtherAtt;
    /**
     * 是否能删除别人附件 *
     */
    private boolean canDeleteOtherAtt;

    /**
     * 是否包含处理意见文本域，not null
     */
    private boolean haveOpinionText;
    /**
     * 当前任务能否编辑表单，not null
     */
    private boolean haveAddSign;
    /**
     * 是否包含代理处理，not null
     */
    private boolean haveDelegate;
    /**
     * 当前任务能否允许APP端操作，not null
     */
    private boolean canAppEdit;


    private static final Logger LOG = LoggerFactory.getLogger(TTaskDetail.class);


    /**
     * 获取下一步任务信息Map
     *
     * @return < 操作类型值 , [下一步任务信息集合] >
     */
    public Map<String, Collection<TNextTaskInfo>> getMapOperation2TNextTaskInfo() {
        Map<String, Collection<TNextTaskInfo>> mapOperation2TNextTaskInfo = new HashMap<>();
        if (this.nextTaskInfos == null || this.nextTaskInfos.size() <= 0) {
            return mapOperation2TNextTaskInfo;
        }
        for (TNextTaskInfo nextTaskInfo : this.nextTaskInfos) {
            Collection<TNextTaskInfo> tNextTaskInfos = mapOperation2TNextTaskInfo.get(nextTaskInfo.getOperation());
            if (tNextTaskInfos == null) {
                tNextTaskInfos = Lists.newArrayList();
            }
            tNextTaskInfos.add(nextTaskInfo);
            mapOperation2TNextTaskInfo.put(nextTaskInfo.getOperation(), tNextTaskInfos);
        }
        return mapOperation2TNextTaskInfo;
    }

    public TTaskDetail() {

    }

    public TTaskDetail(Task task, Map<String, Object> variables) {
        this.task = task;

        if (this.task.getProcessDefinitionId() != null) {
            String[] split = this.task.getProcessDefinitionId().split(":");
            this.processDefinitionKey = split[0];
        } else {
            LOG.error("[TTaskDetail.TTaskDetail]ProcessDefinitionId id null!taskId:" + task.getId());
        }

        this.variables = variables;
        parseVariables(this.task.getTaskDefinitionKey(), variables);
    }

    public void parseVariables(String taskDefinitionKey, Map<String, Object> variables) {
        HashMap<String, Object> mapCurTask = (HashMap<String, Object>) variables.get(taskDefinitionKey);
        this.curRoleIds = BpmnVariableUtil.encodeRoleIds(mapCurTask);
        this.curOrganizationIds = BpmnVariableUtil.encodeCurOrganizationIds(mapCurTask);
        this.nextTaskInfos = BpmnVariableUtil.encodeTNextTaskInfos(mapCurTask);


        this.canEditPage = BpmnVariableUtil.encodeCanEditPage(mapCurTask);
        this.canUpdateAtt = BpmnVariableUtil.encodeCanUpdateAtt(mapCurTask);
        this.canDownloadAtt = BpmnVariableUtil.encodeCanDownloadAtt(mapCurTask);
        this.canDeleteAtt = BpmnVariableUtil.encodeCanDeleteAtt(mapCurTask);
        this.canReadAtt = BpmnVariableUtil.encodeCanReadAtt(mapCurTask);
        this.canUploadAtt = BpmnVariableUtil.encodeUploadAtt(mapCurTask);
        this.canUpdateOtherAtt = BpmnVariableUtil.encodeUpdateOtherAtt(mapCurTask);
        this.canDeleteOtherAtt = BpmnVariableUtil.encodeDeleteOtherAtt(mapCurTask);
        this.haveOpinionText = BpmnVariableUtil.encodeHaveOpinionText(mapCurTask);
        this.haveAddSign = BpmnVariableUtil.encodeHaveAddSign(mapCurTask);
        this.haveDelegate = BpmnVariableUtil.encodeHaveDelegate(mapCurTask);
        this.canAppEdit = BpmnVariableUtil.encodeCanAppEdit(mapCurTask);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Collection<String> getCurRoleIds() {
        return curRoleIds;
    }

    public void setCurRoleIds(Collection<String> curRoleIds) {
        this.curRoleIds = curRoleIds;
    }

    public Collection<TNextTaskInfo> getNextTaskInfos() {
        return nextTaskInfos;
    }

    public void setNextTaskInfos(Collection<TNextTaskInfo> nextTaskInfos) {
        this.nextTaskInfos = nextTaskInfos;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public boolean isCanUpdateAtt() {
        return canUpdateAtt;
    }

    public void setCanUpdateAtt(boolean canUpdateAtt) {
        this.canUpdateAtt = canUpdateAtt;
    }

    public boolean isCanDownloadAtt() {
        return canDownloadAtt;
    }

    public void setCanDownloadAtt(boolean canDownloadAtt) {
        this.canDownloadAtt = canDownloadAtt;
    }

    public boolean isCanDeleteAtt() {
        return canDeleteAtt;
    }

    public void setCanDeleteAtt(boolean canDeleteAtt) {
        this.canDeleteAtt = canDeleteAtt;
    }

    public boolean isCanReadAtt() {
        return canReadAtt;
    }

    public void setCanReadAtt(boolean canReadAtt) {
        this.canReadAtt = canReadAtt;
    }

    public static Logger getLog() {
        return LOG;
    }

    public boolean isCanEditPage() {
        return canEditPage;
    }

    public void setCanEditPage(boolean canEditPage) {
        this.canEditPage = canEditPage;
    }

    public boolean isCanUploadAtt() {
        return canUploadAtt;
    }

    public void setCanUploadAtt(boolean canUploadAtt) {
        this.canUploadAtt = canUploadAtt;
    }

    public boolean isCanUpdateOtherAtt() {
        return canUpdateOtherAtt;
    }

    public void setCanUpdateOtherAtt(boolean canUpdateOtherAtt) {
        this.canUpdateOtherAtt = canUpdateOtherAtt;
    }

    public boolean isCanDeleteOtherAtt() {
        return canDeleteOtherAtt;
    }

    public void setCanDeleteOtherAtt(boolean canDeleteOtherAtt) {
        this.canDeleteOtherAtt = canDeleteOtherAtt;
    }

    public Collection<String> getCurOrganizationIds() {
        return curOrganizationIds;
    }

    public void setCurOrganizationIds(Collection<String> curOrganizationIds) {
        this.curOrganizationIds = curOrganizationIds;
    }

    public boolean isHaveOpinionText() {
        return haveOpinionText;
    }

    public void setHaveOpinionText(boolean haveOpinionText) {
        this.haveOpinionText = haveOpinionText;
    }

    public boolean isHaveAddSign() {
        return haveAddSign;
    }

    public void setHaveAddSign(boolean haveAddSign) {
        this.haveAddSign = haveAddSign;
    }

    public boolean isHaveDelegate() {
        return haveDelegate;
    }

    public void setHaveDelegate(boolean haveDelegate) {
        this.haveDelegate = haveDelegate;
    }

    public boolean isCanAppEdit() {
        return canAppEdit;
    }

    public void setCanAppEdit(boolean canAppEdit) {
        this.canAppEdit = canAppEdit;
    }
}
