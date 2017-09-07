package org.jeff.javatool.tool.myactiviti.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.result.ControllerRequestParam;
import org.jeff.javatool.tool.myactiviti.entity.result.JsonData;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcCustomService;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcessProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 流程图相关
 * Created by weijianfu on 2017/4/18.
 */
@Controller
@RequestMapping("/process/diagram")
public class ProcDiagramController {

    @Autowired
    private IWFProcessProvider processProvider;
    @Autowired
    private IWFProcCustomService procCustomService;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 查询静态流程json，用于显示静态流程指引
     *
     * @param param
     * @return 流程json，不包含动态信息
     */
    @RequestMapping(value = "static", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<TCustomProcess> staticJson(@RequestBody ControllerRequestParam<String> param) {
        try {
            String procDefId = param.getData();
            Result<TCustomProcess> result = procCustomService.queryStaticProcJson(procDefId, null);
            if (result.error()) {
                return JsonData.fail(result.getDetail());
            }
            return JsonData.success(result.getValue(), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }


    /**
     * 查询动态流程json，用于显示动态流程指引，高亮显示、动态信息显示
     *
     * @param param
     * @return 流程json，包含动态信息
     */
    @RequestMapping(value = "dynamic", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<TCustomProcess> dynamicJson(@RequestBody ControllerRequestParam<String> param) {
        try {
            String procInstId = param.getData();
            Result<TCustomProcess> result = procCustomService.queryDynamicProcJson(procInstId, null);
            if (result.error()) {
                return JsonData.fail(result.getDetail());
            }
            return JsonData.success(result.getValue(), "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }
}
