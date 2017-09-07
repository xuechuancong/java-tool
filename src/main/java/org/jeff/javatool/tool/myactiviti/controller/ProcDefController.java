package org.jeff.javatool.tool.myactiviti.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.WFProcessProviderImpl;
import org.jeff.javatool.tool.myactiviti.config.QueryOrderType;
import org.jeff.javatool.tool.myactiviti.config.QueryProcessDefinitionOrderKeyType;
import org.jeff.javatool.tool.myactiviti.entity.TProcDefListCondition;
import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessDefinitionCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.*;
import org.jeff.javatool.tool.myactiviti.entity.vo.TProcessDefinitionListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 流程定义相关
 * Created by weijianfu on 2017/5/16.
 */
@Controller
@RequestMapping("/process/def")
public class ProcDefController {

    @Autowired
    private WFProcessProviderImpl processProvider;

    private Log log = LogFactory.getLog(this.getClass());


    /**
     * 查询流程定义列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JsonData<List<TProcessDefinitionListVO>> staticJson(
            @RequestBody ControllerRequestParam<TProcDefListCondition> param) {
        try {
            TProcDefListCondition tProcDefListCondition = param.getData();
            QueryProcessDefinitionCondition queryCondition = new QueryProcessDefinitionCondition();
            queryCondition.setProcessDefinitionKey(tProcDefListCondition.getProcessDefinitionKey());
            queryCondition.setProcessDefinitionCategory(tProcDefListCondition.getProcessDefinitionCategory());
            queryCondition.setIsLatestVersion(true);
            queryCondition.setOrderType(QueryOrderType.DESC);
            queryCondition.setOrderKeyType(QueryProcessDefinitionOrderKeyType.ID);
            Result<Pager<TProcessDefinitionList>> result = processProvider.queryProcessDefinitionList(queryCondition, tProcDefListCondition.getOffset(), tProcDefListCondition.getLimit(), null);
            if (result.error()) {
                return JsonData.fail(result.getDetail());
            }
            Pager<TProcessDefinitionList> value = result.getValue();
            List<TProcessDefinitionList> dataList = value.getDatas();
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrPage(value.getCurrPage());
            pageInfo.setPageSize(value.getLimit());
            pageInfo.setTotalCount(value.getTotalCount());
            List<TProcessDefinitionListVO> resultList = TProcessDefinitionListVO.parse(dataList);
            return JsonData.success(resultList, pageInfo, "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonData.fail(e.getMessage());
        }
    }

}
