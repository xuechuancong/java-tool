package org.jeff.javatool.tool.myactiviti.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.jeff.javatool.tool.myactiviti.domain.vo.AddSignProductVO;
import org.jeff.javatool.tool.myactiviti.domain.vo.DelegateProductVO;
import org.jeff.javatool.tool.myactiviti.entity.THandleTaskInfo;
import org.jeff.javatool.tool.myactiviti.entity.TTaskDetail;
import org.jeff.javatool.tool.myactiviti.entity.result.JsonData;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IWFTaskProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程操作
 * Created by weijianfu on 2017/8/15.
 */
@Controller
@RequestMapping("/process/oper")
public class ProcOperController {

    @Autowired
    private IWFTaskProvider taskProvider;
//    @Autowired
//    private IProcessVariableManageService processVariableManageService;

    /**
     * 加签
     *
     * @param vo
     * @return
     */
    @RequestMapping("addSign")
    @ResponseBody
    public JsonData addSign(@RequestBody AddSignProductVO vo) {
        if (vo.getTaskId() == null || vo.getUserIds() == null || vo.getUserIds().equals("")) {
            return JsonData.fail("参数不能为空");
        }
        List<String> userIdsList = Lists.newArrayList(vo.getUserIds().split(","));
        Result<List<Task>> listResult = this.addSignDetail(vo.getTaskId(), userIdsList, vo.getTaskShow());
        if (!listResult.success()) {
            return JsonData.fail(listResult.getDetail());
        }
        List<Task> taskList = listResult.getValue();
        //发短信
//        processInfaceService.sendMsg(taskList, "1", null, null, null);//TODO
        return JsonData.success(getResult(taskList), "成功");
    }

    /**
     * 委托
     *
     * @param vo userId1,userId2...
     */
    @RequestMapping("delegate")
    @ResponseBody
    public JsonData delegate(@RequestBody DelegateProductVO vo) {
        if (vo.getTaskId() == null || vo.getUserIds() == null || vo.getUserIds().equals("")) {
            return JsonData.fail("委托失败");
        }
        List<String> userIdsList = Lists.newArrayList(vo.getUserIds().split(","));

        // 查看是否是被授权的
//        if (haveAuthorized(vo.getTaskId(), userIdsList, vo.getTaskDefId(), vo.getProcDefKey())) {
//            return JsonData.fail("该环节是被授权的不能委托他人处理");
//        }

        Result<List<Task>> listResult = this.delegateDetail(vo.getTaskId(), userIdsList, vo.getTaskShow());
        if (!listResult.success()) {
            return JsonData.fail(listResult.getDetail());
        }
        List<Task> taskList = listResult.getValue();
        //发短信
//        processInfaceService.sendMsg(taskList, "1", null, null, null);//TODO
        return JsonData.success(getResult(taskList), "成功");
    }


    private Map<String, Object> getResult(List<Task> taskList) {
        List<Map<String, String>> tasks = Lists.newArrayList();
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                Map<String, String> mapInfo = Maps.newHashMap();
                mapInfo.put("id", task.getId());
                mapInfo.put("name", task.getName());
                tasks.add(mapInfo);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("tasks", tasks);

        return result;
    }


    public Result<List<Task>> delegateDetail(String taskId, Collection<String> userIds, Map<String, Object> variables) {
        if (taskId == null || userIds == null || userIds.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail(taskId, null);
        TTaskDetail curTask = tTaskDetailResult.getValue();
        if (curTask == null) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        String curName = curTask.getTask().getName();
        Collection<THandleTaskInfo> delegateTaskInfos = Lists.newArrayList();
        for (String userId : userIds) {
            delegateTaskInfos.add(new THandleTaskInfo(userId, curName + "-委托"));
        }

        Result<List<Task>> result = taskProvider.delegate(taskId, delegateTaskInfos, null, null);
        if (result.success()) {
            List<Task> taskList = result.getValue();
            if (!CollectionUtils.isEmpty(taskList)) {
                for (Task task : taskList) {
                    // 加工前端传过来的变量集
                    Map<String, Object> variablesReal = null;
                    if (variables != null && !variables.isEmpty()) {
//                        variablesReal = processVariableManageService.setShowVariable(curTask.getTask()
//                                .getProcessInstanceId(), task.getId(), variables);
//                        taskProvider.setVariable(task.getId(), variablesReal, null);
                        taskProvider.setVariable(task.getId(), variables, null);
                    }

                }
            }
        }
        return result;
    }


    public Result<List<Task>> addSignDetail(String taskId, Collection<String> userIds, Map<String, Object> variables) {
        if (taskId == null || userIds == null || userIds.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail(taskId, null);
        TTaskDetail curTask = tTaskDetailResult.getValue();
        if (curTask == null) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        String curName = curTask.getTask().getName();
        Collection<THandleTaskInfo> addSignTaskInfos = Lists.newArrayList();
        for (String userId : userIds) {
            addSignTaskInfos.add(new THandleTaskInfo(userId, curName + "-会签"));
        }

        Result<List<Task>> result = taskProvider.addSign(taskId, addSignTaskInfos, null, null);
        if (result.success()) {
            List<Task> taskList = result.getValue();
            if (!CollectionUtils.isEmpty(taskList)) {
                for (Task task : taskList) {
                    // 加工前端传过来的变量集
                    Map<String, Object> variablesReal = null;
                    if (variables != null && !variables.isEmpty()) {
//                        variablesReal = processVariableManageService.setShowVariable(curTask.getTask()
//                                .getProcessInstanceId(), task.getId(), variables);
//                        taskProvider.setVariable(task.getId(), variablesReal, null);
                        taskProvider.setVariable(task.getId(), variables, null);
                    }

                }
            }
        }
        return result;
    }

}
