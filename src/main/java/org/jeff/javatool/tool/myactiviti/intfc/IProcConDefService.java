package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDef;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcConDefCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.Map;

/**
 * 自定义流程常量配置服务
 */
public interface IProcConDefService {

	 /**
     * 更新
     * @param procConDef 更新实体
     * @param ex 预留字段
     * @return
     */
    public Result<Integer> update(ProcConDef procConDef, Map<String, Object> ex) throws Exception;
    
    /**
     * 查询列表
     * @param condition 查询条件
     * @param ex 预留字段
     * @return
     */
    public Result<Pager<ProcConDef>> queryList(QueryProcConDefCondition condition, Map<String, Object> ex) throws Exception;

    /**
     * 录入
     * @param procConDef 录入实体
     * @param ex 预留字段
     * @return
     */
    public Result<Integer> insert(ProcConDef procConDef, Map<String, Object> ex) throws Exception;
}
