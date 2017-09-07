package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.domain.entity.ProcJob;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcJobCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 流程自动完成任务服务
 * Created by weijianfu on 2017/4/27.
 */
public interface IProcAutoCompleteJobService {
	
	/**
     * 更新
     * @param procJob 更新实体
     * @param ex 预留字段
     * @return
     */
    public Result<Integer> update(ProcJob procJob, Map<String, Object> ex);
    
    /**
     * 查询列表
     * @param condition 查询条件
     * @param ex 预留字段
     * @return
     */
    public Result<Pager<ProcJob>> queryList(QueryProcJobCondition condition, Map<String, Object> ex);

    /**
     * 录入
     * @param procJob 录入实体
     * @param ex 预留字段
     * @return
     */
    public Result<Integer> insert(ProcJob procJob, Map<String, Object> ex);
    
    public Result<Integer> saveOrUpdate(List<ProcJob> procJob, Map<String, Object> ex);

}
