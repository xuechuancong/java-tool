package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionDetail;
import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionList;
import org.jeff.javatool.tool.myactiviti.entity.TTaskDefinitionList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessDefinitionCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作流-流程相关服务
 * Created by weijianfu on 2016/11/22.
 */
public interface IWFProcessProvider {

    /**
     * 查询工作流列表信息
     *
     * @param condition 搜索条件
     * @param offset    分页offset
     * @param limit     分页limit
     * @param ex        预留字段
     * @return
     */
    public Result<Pager<TProcessDefinitionList>> queryProcessDefinitionList(QueryProcessDefinitionCondition condition, int offset, int limit, Map<String, Object> ex);

    /**
     * 查询工作流详情信息
     *
     * @param processDefinitionId 流程定义ID
     * @param ex                  预留字段
     * @return
     */
    public Result<TProcessDefinitionDetail> queryProcessDefinitionDetail(String processDefinitionId, Map<String, Object> ex);

    /**
     * 查询流程定义中的任务定义
     * @param processDefinitionId   必填，流程定义Id
     * @param roleIds               选填，可null，包含的角色Id集合
     * @param ex                    预留字段
     * @return
     */
    public Result<List<TTaskDefinitionList>> queryTaskDefinitionList(String processDefinitionId, Collection<String> roleIds, Map<String, Object> ex);


    /**
     * 查询流程第一步任务的定义KEY
     * @param procDefId     流程定义ID
     * @param ex            预留字段
     * @return 流程json，包含动态信息
     */
    public Result<String> queryFirstTaskDefinitionKey(String procDefId, Map<String, Object> ex);

}
