package org.jeff.javatool.tool.myactiviti.intfc;

import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工作流-任务相关服务
 * Created by weijianfu on 2016/11/22.
 */
public interface IWFTaskProvider {

    /**
     * 生成新的任务
     *
     * @param processDefinitionKey 流程定义的key，对应{@link org.activiti.engine.repository.ProcessDefinition#getKey()}
     * @param userId               用户Id
     * @param variables            自定义字段，会跟随整个task的生命周期
     * @param ex                   预留扩展字段
     * @return
     */
    public Result<TProcessInstance> generateTask(String processDefinitionKey, String userId, Map<String, Object> variables, Map<String, Object> ex);

    /**
     * 查询任务列表
     *
     * @param condition 查询条件
     * @param offset    分页offset
     * @param limit     分页limit
     * @param ex        预留扩展字段
     * @return
     */
    public Result<Pager<TTaskList>> queryTaskList(QueryTaskCondition condition, int offset, int limit, Map<String, Object> ex);

    /**
     * 查询任务详细信息
     *
     * @param taskId 任务ID
     * @param ex     预留扩展字段
     * @return
     */
    public Result<TTaskDetail> queryTaskDetail(String taskId, Map<String, Object> ex);

    /**
     * 完成任务
     *
     * @param processInstanceId 任务流程实例ID
     * @param nextTaskInfos     下一环节执行信息
     * @param taskId            当前任务ID
     * @param operation         操作标识，如1同意 2不同意,结束流程，这些值来源于{@link org.jeff.javatool.tool.myactiviti.entity.TNextTaskInfo#operation}
     * @param variables         自定义字段，会跟随整个task的生命周期
     * @param ex                预留扩展字段
     * @return
     */
    public Result<List<Task>> completeTask(String processInstanceId, Collection<NextTaskInfo> nextTaskInfos, String taskId, String operation, Map<String, Object> variables, Map<String, Object> ex);


    /**
     * 设置任务执行者
     *
     * @param taskId 任务ID
     * @param userId 用户ID
     * @param ex     预留扩展字段
     * @return
     */
    public Result<Void> setAssignee(String taskId, String userId, Map<String, Object> ex);


    /**
     * 设置任务已阅时间
     *
     * @param taskId
     * @param dueDate
     * @return
     */
    public Result<Void> setTaskDueDate(String taskId, Date dueDate);


    /**
     * 加签操作：不嵌入流程当中，由当前处理人选择加签人，当前处理人也可不等加签人的处理意见直接处理流程
     *
     * @param taskId           当前任务ID
     * @param addSignTaskInfos 加签任务信息集合
     * @param variables        自定义字段，会跟随整个task的生命周期
     * @return 加签任务集合
     */
    public Result<List<Task>> addSign(String taskId, Collection<THandleTaskInfo> addSignTaskInfos, Map<String, Object> variables, Map<String, Object> ex);

    /**
     * 委托操作:本流程节点全权委托被委托人审批
     *
     * @param taskId            当前任务ID
     * @param delegateTaskInfos 会签任务信息集合
     * @param variables         自定义字段，会跟随整个task的生命周期
     * @return 委托任务集合
     */
    public Result<List<Task>> delegate(String taskId, Collection<THandleTaskInfo> delegateTaskInfos, Map<String, Object> variables, Map<String, Object> ex);

    /**
     * 新增/修改流程变量，如果变量名已存在，则覆盖
     *
     * @param taskId    当前任务ID
     * @param variables 变量集合
     * @param ex        预留扩展字段
     * @return
     */
    public Result<Void> setVariable(String taskId, Map<String, ? extends Object> variables, Map<String, Object> ex);


    /**
     * 根据任务id,生成同样的任务(id不同)
     * 其中一个任务完成, 则其它任务自动完成
     *
     * @param taskIdRef 源任务ID(被拷贝者)
     * @param newUserId 新任务的执行者ID
     * @param ex
     * @return 新任务实体
     */
    public Result<Task> addSameNormalTask(String taskIdRef, String newUserId, Map<String, Object> ex);
}
