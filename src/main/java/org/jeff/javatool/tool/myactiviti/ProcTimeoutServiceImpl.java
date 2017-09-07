package org.jeff.javatool.tool.myactiviti;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.domain.TProcTimeoutMapper;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeout;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeoutExample;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IProcTimeoutService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程任务超时标记
 * Created by weijianfu on 2017/8/18.
 */
@Service
public class ProcTimeoutServiceImpl implements IProcTimeoutService {

    @Autowired
    private TProcTimeoutMapper procTimeoutMapper;

    private Log log = LogFactory.getLog(this.getClass());


    @Override
    public Result<Integer> insert(TProcTimeout procTimeout, Map<String, Object> ex) {
        if (procTimeout == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int insert = procTimeoutMapper.insert(procTimeout);
            return ResultFactory.success(insert);
        } catch (Throwable e) {
            log.error("[ProcTimeoutServiceImpl.insert] Error!" +
                    "procTimeout:" + JsonUtil.serialize(procTimeout) + "; " +
                    "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Integer> update(TProcTimeout procTimeout, Map<String, Object> ex) {
        if (procTimeout == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int insert = procTimeoutMapper.updateByPrimaryKeySelective(procTimeout);
            return ResultFactory.success(insert);
        } catch (Throwable e) {
            log.error("[ProcTimeoutServiceImpl.update] Error!" +
                    "procTimeout:" + JsonUtil.serialize(procTimeout) + "; " +
                    "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<List<TProcTimeout>> queryList(TProcTimeoutExample example, Map<String, Object> ex) {
        if (example == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        List<TProcTimeout> tProcTimeouts = procTimeoutMapper.selectByExample(example);

        return ResultFactory.success(tProcTimeouts);
    }
}
