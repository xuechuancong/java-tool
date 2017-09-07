package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.THistoricInstanceList;
import org.jeff.javatool.tool.myactiviti.entity.THistoricTaskList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.Map;

/**
 * 工作流-历史数据查询服务
 * Created by weijianfu on 2016/11/25.
 */
public interface IWFHistoryProvider {


    /**
     * 查询历史任务列表信息
     *
     * @param condition 搜索条件
     * @param offset 分页offset
     * @param limit  分页limit
     * @param ex     预留字段
     * @return
     */
    public Result<Pager<THistoricTaskList>> queryHistoricTaskList(QueryHistoricTaskCondition condition, int offset, int limit, Map<String, Object> ex);

    /**
     * 查询历史流程列表信息
     *
     * @param condition 搜索条件
     * @param offset 分页offset
     * @param limit  分页limit
     * @param ex     预留字段
     * @return
     */
    public Result<Pager<THistoricInstanceList>> queryHistoricInstanceList(QueryHistoricInstanceCondition condition, int offset, int limit, Map<String, Object> ex);


}
