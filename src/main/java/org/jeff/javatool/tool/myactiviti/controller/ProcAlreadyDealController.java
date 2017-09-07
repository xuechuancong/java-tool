package org.jeff.javatool.tool.myactiviti.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.WFHistoryProviderImpl;
import org.jeff.javatool.tool.myactiviti.config.ConstantsAboutProc;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessListPage;
import org.jeff.javatool.tool.myactiviti.entity.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 已办相关 Created by wenxianfu on 2017/5/16.
 */
@Controller
@RequestMapping("/process/queryList")
public class ProcAlreadyDealController {

    @Autowired
    private WFHistoryProviderImpl historyProvider;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 查询已办列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<List<TaskShowListVo>> list(@RequestBody ControllerRequestParam<QueryProcessListPage> condition,
                                               HttpServletRequest request) {
        JsonData<List<TaskShowListVo>> jsonData = new JsonData<List<TaskShowListVo>>();
        jsonData.setResult(jsonData.getResult().SUCCESS);
        jsonData.setMessage("OK");
        PageInfo pageInfo = new PageInfo();
        try {

            QueryProcessListPage pagerQuery = condition.getData();
            String taskListType = pagerQuery.getTaskListType();
            Pager<TaskShowListVo> resultPager = new Pager<TaskShowListVo>(0, 0, 0, new ArrayList<TaskShowListVo>());
//			if (ConstantsAboutProc.TASK_LIST_TYPE.WAIT_DO.equals(taskListType)) {
//				resultPager = processInfaceService.findWaittingDealTasks(pagerQuery, pagerQuery.getOffset() + 1,
//						pagerQuery.getLimit());
//			}
//			if (ConstantsAboutProc.TASK_LIST_TYPE.AREADY_DO.equals(taskListType)
//					|| ConstantsAboutProc.TASK_LIST_TYPE.MY_AREADY_DO.equals(taskListType)
//					|| ConstantsAboutProc.TASK_LIST_TYPE.MY_AREADY_DONE.equals(taskListType)
//					|| ConstantsAboutProc.TASK_LIST_TYPE.AREADY_DO_NO_BODY.equals(taskListType)) {
//				resultPager = processInfaceService.findMultiTypeTasks(pagerQuery, pagerQuery.getOffset() + 1,
//						pagerQuery.getLimit());
//			}

//			if (ConstantsAboutProc.TASK_LIST_TYPE.PROCESS_REQUEST.equals(taskListType)) {
//
//				resultPager = processInfaceService.queryProcessDefinitionList(pagerQuery, pagerQuery.getOffset() + 1,
//						pagerQuery.getLimit());
//			}
            if (ConstantsAboutProc.TASK_LIST_TYPE.WAIT_READ.equals(taskListType)
                    || ConstantsAboutProc.TASK_LIST_TYPE.AREADY_READ.equals(taskListType)) {

            }

            if (resultPager != null) {
                pageInfo.setCurrPage(resultPager.getCurrPage());
                pageInfo.setPageSize(pagerQuery.getLimit());
                pageInfo.setTotalCount(resultPager.getTotalCount());
                jsonData.setData(resultPager.getDatas());
            } else {
                pageInfo.setCurrPage(pagerQuery.getOffset() + 1);
                pageInfo.setPageSize(0);
                pageInfo.setTotalCount(0);
                jsonData.setData(new ArrayList<TaskShowListVo>());
            }
            jsonData.setExtData(pageInfo);
            return jsonData;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            jsonData.setResult(jsonData.getResult().FAILED);
            jsonData.setMessage("查询失败");
            return jsonData;
        }
    }
}
