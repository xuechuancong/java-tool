package org.jeff.javatool.tool.myactiviti.controller;

import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.domain.vo.*;
import org.jeff.javatool.tool.myactiviti.entity.THistoricInstanceList;
import org.jeff.javatool.tool.myactiviti.entity.TTaskJump;
import org.jeff.javatool.tool.myactiviti.entity.TaskUserVO;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.ControllerRequestParam;
import org.jeff.javatool.tool.myactiviti.entity.result.JsonData;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFHistoryProvider;
import org.jeff.javatool.tool.myactiviti.intfc.IWFManagementProvider;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/process/monitor")
public class ProcMonitorController {
    @Autowired
    private IWFManagementProvider iWFManagementProvider;
    @Autowired
    private IWFHistoryProvider historyProvider;

    /*
     * 流程重新指派人
     */
    @RequestMapping(value = "/resetAssignee")
    @ResponseBody
    public JsonData resetAssignee(@RequestBody ControllerRequestParam<ResetAssigneeVO> param) {
        ResetAssigneeVO vo = param.getData();
        String processId = vo.getProcessId();
        String taskId = vo.getTaskId();
        String resetUserId = vo.getResetUserId();
        Map<String, Object> taskShow = vo.getTaskShow();

        try {
            if (StringUtil.isBlank(taskId)) {
                return JsonData.fail("指定失败，请选择要指定的环节");
            }
            if (StringUtil.isBlank(resetUserId)) {
                return JsonData.fail("指定失败，请选择要指定的人");
            }

            // 加工前端传过来的变量集
//            Map<String, Object> variablesReal = null;
//            if (taskShow != null && !taskShow.isEmpty()) {
//                variablesReal = processVariableManageService.setShowVariable(processId, taskId, taskShow);
//            }
//            iWFManagementProvider.resetAssignee(taskId, resetUserId, variablesReal, null);
            iWFManagementProvider.resetAssignee(taskId, resetUserId, taskShow, null);
            return JsonData.success("指定成功");
        } catch (Exception ex) {
            return JsonData.fail("指定失败，系统异常，请联系管理员");
        }

    }

    /*
     * 结束流程
     */
    @RequestMapping(value = "/suspendProcess")
    @ResponseBody
    public JsonData suspendProcess(@RequestBody ControllerRequestParam<SuspendProcessVO> param) {
        SuspendProcessVO vo = param.getData();
        try {
            if (StringUtil.isBlank(vo.getProcessId())) {
                return JsonData.fail("操作失败，请选择要结束的流程");
            }
            iWFManagementProvider.deleteProcess(vo.getProcessId(), "管理员删除", null);
            return JsonData.success("操作成功");
        } catch (Exception ex) {
            return JsonData.fail("指定失败，系统异常，请联系管理员");
        }

    }

    /*
     * 跳转
     */
    @RequestMapping(value = "/jump")
    @ResponseBody
    public JsonData jump(@RequestBody ControllerRequestParam<JumpVO> param) {
        JumpVO vo = param.getData();
        try {
            if (StringUtil.isBlank(vo.getProcessInstanceId())) {
                return JsonData.fail("操作失败，请选择流程");
            } else if (StringUtil.isBlank(vo.getTargetTaskDefinitionKey())) {
                return JsonData.fail("操作失败，请选择任务");
            } else if (StringUtil.isBlank(vo.getUserId())) {
                return JsonData.fail("操作失败，请选择用户");
            }
            // 加工前端传过来的变量集
//            Map<String, Object> variablesReal = null;
//            if (vo.getTaskShow() != null && !vo.getTaskShow().isEmpty()) {
//                variablesReal = processVariableManageService.setShowVariable(vo.getProcessInstanceId(), vo.getTaskId(), vo.getTaskShow());
//            }
//            Result<List<Task>> result = iWFManagementProvider.jump(vo.getProcessInstanceId()
//                    , vo.getTargetTaskDefinitionKey(), vo.getUserId(), variablesReal, vo.getEx());
            Result<List<Task>> result = iWFManagementProvider.jump(vo.getProcessInstanceId()
                    , vo.getTargetTaskDefinitionKey(), vo.getUserId(), vo.getTaskShow(), vo.getEx());
            if (result.error()) {
                return JsonData.fail(result.getDetail());
            }
            return JsonData.success("操作成功");
        } catch (Exception ex) {
            return JsonData.fail("指定失败，系统异常，请联系管理员");
        }

    }

    /*
     * 跳转任务定义列表
     */
    @RequestMapping(value = "/procTaskDefList")
    @ResponseBody
    public JsonData queryProcTaskDefList(@RequestBody ControllerRequestParam<QueryProcTaskDefListVO> param) {
        QueryProcTaskDefListVO vo = param.getData();
        try {
            if (StringUtil.isBlank(vo.getProcDefId())) {
                return JsonData.fail("操作失败，请选择流程定义ID");
            }
            Result<List<TTaskJump>> result = iWFManagementProvider.queryProcTaskDefList(vo.getProcDefId(), null);
            if (result.error()) {
                return JsonData.fail(result.getDetail());
            }
            return JsonData.success(result.getValue(), "操作成功");
        } catch (Exception ex) {
            return JsonData.fail("指定失败，系统异常，请联系管理员");
        }

    }


    /*
     * 通过流程实例id查当前环节信息
	 */
    @RequestMapping(value = "/queryTaskByProcessId")
    @ResponseBody
    public JsonData queryTaskByProcessId(@RequestBody ControllerRequestParam<QueryTaskByProcessIdVO> param) {
        QueryTaskByProcessIdVO vo = param.getData();
        try {
            List<TaskUserVO> taskUserList = new ArrayList<TaskUserVO>();
            QueryHistoricInstanceCondition condition = new QueryHistoricInstanceCondition();
            if ("".equals(vo.getProcessId()) || vo.getProcessId() == null) {
                return JsonData.fail("操作失败，流程实例id不能为空");
            }
            condition.setProcInstanceId(vo.getProcessId());
            Result<Pager<THistoricInstanceList>> results = historyProvider.queryHistoricInstanceList(condition, 0,
                    Integer.MAX_VALUE, null);
            if (results != null) {
                Pager<THistoricInstanceList> taskListPage = results.getValue();
                List<THistoricInstanceList> historicTaskList = taskListPage.getDatas();
                if (historicTaskList != null && historicTaskList.size() > 0) {
                    THistoricInstanceList historicTask = historicTaskList.get(0);
                    List<Task> taskList = historicTask.getCurTaskList();
                    for (Task task : taskList) {
                        TaskUserVO taskUserVO = new TaskUserVO();
                        taskUserVO.setProcessId(vo.getProcessId());
                        taskUserVO.setTaskDefId(task.getTaskDefinitionKey());
                        taskUserVO.setTaskId(task.getId());
                        taskUserVO.setTaskName(task.getName());
                        taskUserVO.setUserId(task.getAssignee());
//                        ActIdUserVO actIdUserVO = tActIdUserService.getActIdUserDept(task.getAssignee());
//                        if (actIdUserVO != null) {
//                            taskUserVO.setUserName(actIdUserVO.getUserName());
//                        }
                        taskUserList.add(taskUserVO);
                    }
                }
            }
            if (taskUserList != null && taskUserList.size() > 0) {
                return JsonData.success(taskUserList, "操作成功");
            } else {
                return JsonData.fail("查询失败，找不到记录");
            }
        } catch (Exception ex) {
            return JsonData.fail("操作失败，系统异常，请联系管理员");
        }

    }

}
