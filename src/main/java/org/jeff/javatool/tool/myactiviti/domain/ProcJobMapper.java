package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcJob;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcJobExample;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcJobCondition;

import java.util.List;

public interface ProcJobMapper {
    int countByExample(ProcJobExample example);

    int deleteByExample(ProcJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProcJob record);

    int insertSelective(ProcJob record);

    List<ProcJob> selectByExample(ProcJobExample example);

    ProcJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProcJob record, @Param("example") ProcJobExample example);

    int updateByExample(@Param("record") ProcJob record, @Param("example") ProcJobExample example);

    int updateByPrimaryKeySelective(ProcJob record);

    int updateByPrimaryKey(ProcJob record);
    
    int countList(QueryProcJobCondition condition);

    List<ProcJob> queryList(QueryProcJobCondition condition);
}