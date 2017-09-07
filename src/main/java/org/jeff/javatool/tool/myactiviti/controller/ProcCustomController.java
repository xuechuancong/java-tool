package org.jeff.javatool.tool.myactiviti.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomJson;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessCopy;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessState;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.ControllerRequestParam;
import org.jeff.javatool.tool.myactiviti.entity.result.JsonData;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcCustomService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 自定义流程相关
 * Created by weijianfu on 2017/3/27.
 */
@Controller
@RequestMapping("/process/custom")
public class ProcCustomController {

    @Autowired
    private IWFProcCustomService procCustomService;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 生成/更新自定义流程
     *
     * @param param
     * @return 流程定义KEY
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<String> replace(
            @RequestBody ControllerRequestParam<TCustomProcess> param) {
        try {
            TCustomProcess tCustomProcess = param.getData();
            if (tCustomProcess == null) {
                return JsonData.fail("参数为空");
            }

            String procJson = null;
            if (tCustomProcess.getFlows() != null || tCustomProcess.getTasks() != null) {
                TProcCustomJson tProcCustomJson = new TProcCustomJson();
                tProcCustomJson.setFlows(tCustomProcess.getFlows());
                tProcCustomJson.setTasks(tCustomProcess.getTasks());
                procJson = JsonUtil.serialize(tProcCustomJson);
            }

            TProcCustom procCustom = TProcCustom.build(tCustomProcess, procJson);

            if (!StringUtil.isBlank(procCustom.getProcDefKey()) && procCustom.getVersion() != null) {//更新
                Result<Integer> update = procCustomService.update(procCustom, null);
                if (update.error()) {
                    return JsonData.fail(update.getDetail());
                }
            } else {//录入
                Result<Integer> insert = procCustomService.insert(procCustom, null);
                if (insert.error()) {
                    return JsonData.fail(insert.getDetail());
                }
            }
            return JsonData.success(procCustom.getProcDefKey(), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }


    /**
     * 自定义流程状态修改
     *
     * @param param
     * @return 版本号
     */
    @RequestMapping(value = "state", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<Integer> state(
            @RequestBody ControllerRequestParam<TCustomProcessState> param) {
        try {
            TCustomProcessState customProcessState = param.getData();
//            customProcessState.setOperUserName(userInfo.getUserName());//用户信息
            Result<Integer> update = procCustomService.updateState(customProcessState, null);
            if (update.error()) {
                return JsonData.fail(update.getDetail());
            }
            return JsonData.success(update.getValue(), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }


    /**
     * 自定义流程列表查询
     *
     * @param param
     * @return 自定义流程集合
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<List<TProcCustom>> list(
            @RequestBody ControllerRequestParam<QueryCustomProcessCondition> param) {
        try {
            QueryCustomProcessCondition condition = param.getData();
            Result<Pager<TProcCustom>> procCustomListResult = procCustomService.queryList(condition, null);
            if (procCustomListResult.error()) {
                return JsonData.fail(procCustomListResult.getDetail());
            }
            Pager<TProcCustom> procCustomPager = procCustomListResult.getValue();
            List<TProcCustom> procCustomList = procCustomPager.getDatas();
            if (procCustomList == null || procCustomList.size() <= 0) {
                return JsonData.success(procCustomList, procCustomPager.buildPageInfo(), "成功");
            }
            for (TProcCustom tProcCustom : procCustomList) {
                tProcCustom.setJson(null);//列表页不需要json中的信息
            }
            return JsonData.success(procCustomList, procCustomPager.buildPageInfo(), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }

    /**
     * 自定义流程详情查询
     *
     * @param param
     * @return 自定义流程集合
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<TProcCustom> detail(
            @RequestBody ControllerRequestParam<String> param) {
        try {
            String procDefKey = param.getData();
            QueryCustomProcessCondition condition = new QueryCustomProcessCondition();
            condition.setProcDefKey(procDefKey);
            condition.setLimit(Integer.MAX_VALUE);
            condition.setOffset(0);
            Result<Pager<TProcCustom>> procCustomListResult = procCustomService.queryList(condition, null);
            if (procCustomListResult.error()) {
                return JsonData.fail(procCustomListResult.getDetail());
            }
            Pager<TProcCustom> procCustomPager = procCustomListResult.getValue();
            List<TProcCustom> procCustomList = procCustomPager.getDatas();
            if (procCustomList == null || procCustomList.size() <= 0) {
                return JsonData.success(null, "成功");
            }
            return JsonData.success(procCustomList.get(0), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }


    /**
     * 自定义流程刘表查询
     *
     * @param param
     * @return 自定义流程集合
     */
    @RequestMapping(value = "copy", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<TProcCustom> copy(
            @RequestBody ControllerRequestParam<TCustomProcessCopy> param) {
        try {
            TCustomProcessCopy data = param.getData();
            if (data == null || data.getProcDefKey() == null || data.getVersion() == null) {
                return JsonData.fail("参数为空");
            }
            QueryCustomProcessCondition condition = new QueryCustomProcessCondition();
            condition.setProcDefKey(data.getProcDefKey());
            condition.setVersion(data.getVersion());
            condition.setLimit(Integer.MAX_VALUE);
            condition.setOffset(0);
            Result<Pager<TProcCustom>> procCustomListResult = procCustomService.queryList(condition, null);
            if (procCustomListResult.error()) {
                return JsonData.fail(procCustomListResult.getDetail());
            }
            Pager<TProcCustom> procCustomPager = procCustomListResult.getValue();
            List<TProcCustom> datas = procCustomPager.getDatas();
            if (datas == null || datas.size() <= 0) {
                return JsonData.fail("没有查到被复制流程KEY的数据");
            }
            TProcCustom procCustom = datas.get(0);
            procCustom.setProcDefName(procCustom.getProcDefName() + "_副本");
            procCustom.setProcDefKey(null);
            procCustom.setVersion(null);
            procCustom.setState(null);
            Result<Integer> insert = procCustomService.insert(procCustom, null);
            if (insert.error()) {
                return JsonData.fail(insert.getDetail());
            }
            return JsonData.success(procCustom, "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }
}
