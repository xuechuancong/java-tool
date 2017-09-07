package org.jeff.javatool.tool.myactiviti;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.config.SeqConfig;
import org.jeff.javatool.tool.myactiviti.domain.CommonSeqMapper;
import org.jeff.javatool.tool.myactiviti.domain.ProcJobMapper;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcJob;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcJobExample;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcJobCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IProcAutoCompleteJobService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 流程自动完成任务服务
 * Created by weijianfu on 2017/4/27.
 */
@Service
public class ProcAutoCompleteJobServiceImpl implements IProcAutoCompleteJobService {

    @Autowired
    private ProcJobMapper procJobMapper;
    @Autowired
    private CommonSeqMapper commonSeqMapper;

    private Log log = LogFactory.getLog(this.getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> update(ProcJob procJob, Map<String, Object> ex) {
        if (procJob == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            ProcJobExample example = new ProcJobExample();
            ProcJobExample.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(procJob.getId())) {
                criteria.andIdEqualTo(procJob.getId());
            }
            if (!StringUtils.isEmpty(procJob.getProcDefKey())) {
                criteria.andProcDefKeyEqualTo(procJob.getProcDefKey());
            }
            if (!StringUtils.isEmpty(procJob.getTaskDefKey())) {
                criteria.andTaskDefKeyEqualTo(procJob.getTaskDefKey());
            }
            int update = procJobMapper.updateByExampleSelective(procJob, example);
            return ResultFactory.success(update);
        } catch (Throwable e) {
            log.error("[ProcAutoCompleteJobServiceImpl.update] Error!" + "procJob" + JsonUtil.serialize(procJob) + "; "
                    + "ex" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Pager<ProcJob>> queryList(QueryProcJobCondition condition, Map<String, Object> ex) {
        if (condition == null || condition.getLimit() == null || condition.getOffset() == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int totalCount = procJobMapper.countList(condition);
            List<ProcJob> procJobList = procJobMapper.queryList(condition);
            return ResultFactory.success(new Pager<>(condition.getOffset(), condition.getLimit(), totalCount,
                    procJobList));
        } catch (Throwable e) {
            log.error("[ProcAutoCompleteJobServiceImpl.queryList]Error!入参 condition:" + JsonUtil.serialize(condition) + "; ex:"
                    + JsonUtil.serialize(ex), e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> insert(ProcJob procJob, Map<String, Object> ex) {
        if (procJob == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            String id = String.valueOf(commonSeqMapper.selectSeqByName(SeqConfig.SEQ_PROC_JOB));
            procJob.setId(id);
            int insert = procJobMapper.insertSelective(procJob);
            return ResultFactory.success(insert);
        } catch (Throwable e) {
            log.error("[ProcAutoCompleteJobServiceImpl.insert] Error!" + "procJob" + JsonUtil.serialize(procJob) + "; " + "ex"
                    + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> saveOrUpdate(List<ProcJob> list, Map<String, Object> ex) {
        QueryProcJobCondition condition = new QueryProcJobCondition();
        int num = 0;
        for (ProcJob procJob : list) {
            String procDefKey = procJob.getProcDefKey();
            String taskDefKey = procJob.getTaskDefKey();
            condition.setProcDefKey(procDefKey);
            condition.setTaskDefKey(taskDefKey);
            int totalCount = procJobMapper.countList(condition);
            if (totalCount > 0) {
                ProcJobExample example = new ProcJobExample();
                example.createCriteria().andProcDefKeyEqualTo(procDefKey).andTaskDefKeyEqualTo(taskDefKey);
                num = num + procJobMapper.updateByExampleSelective(procJob, example);
            } else {
                Result<Integer> insert = insert(procJob, null);
                num = num + insert.getValue();
            }
        }
        return ResultFactory.success(num);
    }

}
