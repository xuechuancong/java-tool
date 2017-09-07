package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDef;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDefExample;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDefKey;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcConDefCondition;

import java.util.List;

public interface ProcConDefMapper {
    int countByExample(ProcConDefExample example);

    int deleteByExample(ProcConDefExample example);

    int deleteByPrimaryKey(ProcConDefKey key);

    int insert(ProcConDef record);

    int insertSelective(ProcConDef record);

    List<ProcConDef> selectByExample(ProcConDefExample example);

    ProcConDef selectByPrimaryKey(ProcConDefKey key);

    int updateByExampleSelective(@Param("record") ProcConDef record, @Param("example") ProcConDefExample example);

    int updateByExample(@Param("record") ProcConDef record, @Param("example") ProcConDefExample example);

    int updateByPrimaryKeySelective(ProcConDef record);

    int updateByPrimaryKey(ProcConDef record);


    /**
     * 查询符合条件的记录数
     */
    int countList(QueryProcConDefCondition condition);

    /**
     * 查询符合条件的记录
     */
    List<ProcConDef> queryList(QueryProcConDefCondition condition);
}