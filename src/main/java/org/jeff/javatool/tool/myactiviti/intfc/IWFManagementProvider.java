package org.jeff.javatool.tool.myactiviti.intfc;


import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.entity.TTaskJump;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 工作流-人为管理服务，属于流程监控模块
 * Created by weijianfu on 2017/2/22.
 */
public interface IWFManagementProvider {


    /**
     * 重新指派处理人
     *
     * @param taskId    任务Id
     * @param userId    处理人Id
     * @param variables 自定义字段，会跟随整个task的生命周期
     * @param ex        预留字段
     * @return
     */
    public Result<Void> resetAssignee(String taskId, String userId, Map<String, Object> variables, Map<String, Object> ex);

    /**
     * 挂起流程
     * <p/>
     * 注意：a.已生成的各种数据不做任何处理，包括流程引擎数据、业务数据、外部系统数据
     * b.挂起后待办消失
     *
     * @param processInstanceId 流程实例Id
     * @param ex                预留字段
     * @return
     */
    public Result<Void> suspendProcess(String processInstanceId, Map<String, Object> ex);

    /**
     * 删除流程
     * <p/>
     * 注意：a.已生成的各种数据不做任何处理，包括流程引擎数据、业务数据、外部系统数据
     * b.挂起后待办消失
     *
     * @param processInstanceId 流程实例Id
     * @param deleteReason      删除原因
     * @param ex                预留字段
     * @return
     */
    public Result<Void> deleteProcess(String processInstanceId, String deleteReason, Map<String, Object> ex);

    /**
     * 流程节点跳转，接口调用一定要谨慎！
     * <p/>
     * 注意：跳转后，流程已生成、影响的数据不做回滚。
     *
     * @param processInstanceId       流程实例Id
     * @param targetTaskDefinitionKey 目标任务定义Key
     * @param userId                  目标任务执行者ID
     * @param variables               自定义字段，会跟随整个task的生命周期
     * @param ex
     * @return
     */
    public Result<List<Task>> jump(String processInstanceId, String targetTaskDefinitionKey, String userId, Map<String, Object> variables, Map<String, Object> ex);


    /**
     * 流程节点跳转需要的所有任务节点信息,只包含正常任务
     *
     * @param procDefId
     * @param ex
     * @return
     */
    public Result<List<TTaskJump>> queryProcTaskDefList(String procDefId, Map<String, Object> ex);
}
