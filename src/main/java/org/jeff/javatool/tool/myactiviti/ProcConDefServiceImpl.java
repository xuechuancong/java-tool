package org.jeff.javatool.tool.myactiviti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.config.ProcConDefType;
import org.jeff.javatool.tool.myactiviti.config.SeqConfig;
import org.jeff.javatool.tool.myactiviti.domain.CommonSeqMapper;
import org.jeff.javatool.tool.myactiviti.domain.ProcConDefMapper;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDef;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDefKey;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcConDefCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IProcConDefService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 自定义流程常量配置服务
 */
@Service
public class ProcConDefServiceImpl implements IProcConDefService {

    @Autowired
    private CommonSeqMapper commonSeqMapper;
    @Autowired
    private ProcConDefMapper procConDefMapper;
    @Autowired
    private RepositoryService repositoryService;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 修改（根据主键ID修改）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> update(ProcConDef procConDef, Map<String, Object> ex) {
        if (procConDef == null
                || procConDef.getId() == null
                || procConDef.getId().length() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        ProcConDefKey primaryKey = new ProcConDefKey();
        primaryKey.setId(procConDef.getId());
        primaryKey.setDataType(procConDef.getDataType());
        ProcConDef procConDefQuery = procConDefMapper.selectByPrimaryKey(primaryKey);
        if (ProcConDefType.FLOW_TYPE.getType().equals(procConDefQuery.getDataType())
                && (procConDefQuery.getId().equals("1") || procConDefQuery.getId().equals("2") || procConDefQuery.getId().equals("3") || procConDefQuery.getId().equals("4"))) {
            return ResultFactory.error("该流向类型为固有类型，不可修改、不可删除");
        } else if (ProcConDefType.TASK_DEF_CATEGORY.getType().equals(procConDefQuery.getDataType())) {
            return ResultFactory.error("任务定义类型，不可修改、不可删除");
        } else if (ProcConDefType.PROC_DEF_CATEGORY.getType().equals(procConDefQuery.getDataType())
                && "0".equals(procConDef.getState())) {
            String categoryVal = procConDefQuery.getId();
            List<ProcessDefinition> processDefinitioList = repositoryService.createProcessDefinitionQuery().processDefinitionCategory(categoryVal).list();
            if (processDefinitioList != null && processDefinitioList.size() > 0) {
                return ResultFactory.error("该流程类型已有绑定的流程定义，不允许删除");
            }
        }
        try {
            int update = procConDefMapper.updateByPrimaryKeySelective(procConDef);
            return ResultFactory.success(update);
        } catch (Throwable e) {
            log.error("[ProcConDefServiceImpl.update] Error!" + "procConDef" + JsonUtil.serialize(procConDef) + "; "
                    + "ex" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Pager<ProcConDef>> queryList(QueryProcConDefCondition condition, Map<String, Object> ex) {
        if (condition == null || condition.getLimit() == null || condition.getOffset() == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int totalCount = procConDefMapper.countList(condition);
            List<ProcConDef> procCustomList = procConDefMapper.queryList(condition);
            return ResultFactory.success(new Pager<>(condition.getOffset(), condition.getLimit(), totalCount,
                    procCustomList));
        } catch (Throwable e) {
            log.error("[ProcConDefServiceImpl.queryList]Error!入参 condition:" + JsonUtil.serialize(condition) + "; ex:"
                    + JsonUtil.serialize(ex), e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> insert(ProcConDef procConDef, Map<String, Object> ex) throws Exception {
        if (procConDef == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        if (procConDef.getShowOrder() == null) {
            procConDef.setShowOrder(Long.valueOf("9999"));
        }
        if (ProcConDefType.TASK_DEF_CATEGORY.getType().equals(procConDef.getDataType())) {
            return ResultFactory.error("任务定义类型，不可新增");
        }
        procConDef.setCreateTime(new Date());
        try {
            procConDef.setId(String.valueOf(commonSeqMapper.selectSeqByName(SeqConfig.SEQ_PROC_CON_DEF)));
            int insert = procConDefMapper.insertSelective(procConDef);
            return ResultFactory.success(insert);
        } catch (Throwable e) {
            log.error("[ProcConDefServiceImpl.insert] Error!" + "procConDef" + JsonUtil.serialize(procConDef) + "; "
                    + "ex" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

}
