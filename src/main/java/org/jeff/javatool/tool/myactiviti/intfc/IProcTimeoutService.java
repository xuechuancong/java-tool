package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeout;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeoutExample;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 流程任务超时处理
 * Created by weijianfu on 2017/8/18.
 */
public interface IProcTimeoutService {


    /**
     * 录入
     *
     * @param procTimeout
     * @param ex
     * @return
     */
    public Result<Integer> insert(TProcTimeout procTimeout, Map<String, Object> ex);

    /**
     * 更新
     *
     * @param procTimeout
     * @param ex
     * @return
     */
    public Result<Integer> update(TProcTimeout procTimeout, Map<String, Object> ex);

    /**
     *
     *
     * @param example
     * @param ex
     * @return
     */
    public Result<List<TProcTimeout>> queryList(TProcTimeoutExample example, Map<String, Object> ex);

}
