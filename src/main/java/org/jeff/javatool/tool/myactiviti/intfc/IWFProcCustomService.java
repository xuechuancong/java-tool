package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessState;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.Map;

/**
 * 自定义流程服务
 * Created by weijianfu on 2017/3/28.
 */
public interface IWFProcCustomService {
    /**
     * 查询列表
     *
     * @param condition 查询条件
     * @param ex        预留字段
     * @return
     */
    public Result<Pager<TProcCustom>> queryList(QueryCustomProcessCondition condition, Map<String, Object> ex);

    /**
     * 录入
     *
     * @param procCustom 录入实体
     * @param ex         预留字段
     * @return
     */
    public Result<Integer> insert(TProcCustom procCustom, Map<String, Object> ex);

    /**
     * 更新实体内容
     *
     * @param procCustom 更新实体
     * @param ex         预留字段
     * @return
     */
    public Result<Integer> update(TProcCustom procCustom, Map<String, Object> ex);

    /**
     * 更新激活状态
     *
     * @param processState 更新实体
     * @param ex           预留字段
     * @return
     */
    public Result<Integer> updateState(TCustomProcessState processState, Map<String, Object> ex);


    /**
     * 查询静态流程json，用于显示静态流程指引
     *
     * @param procDefId 流程定义ID
     * @param ex        预留字段
     * @return 流程json，不包含动态信息
     */
    public Result<TCustomProcess> queryStaticProcJson(String procDefId, Map<String, Object> ex);

    /**
     * 查询动态流程json，用于显示动态流程指引，高亮显示、动态信息显示
     *
     * @param procInstId 流程实例ID
     * @param ex         预留字段
     * @return 流程json，包含动态信息
     */
    public Result<TCustomProcess> queryDynamicProcJson(String procInstId, Map<String, Object> ex);

}

