package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeout;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeoutExample;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcTimeoutKey;

import java.util.List;

public interface TProcTimeoutMapper {
    int countByExample(TProcTimeoutExample example);

    int deleteByExample(TProcTimeoutExample example);

    int deleteByPrimaryKey(TProcTimeoutKey key);

    int insert(TProcTimeout record);

    int insertSelective(TProcTimeout record);

    List<TProcTimeout> selectByExample(TProcTimeoutExample example);

    TProcTimeout selectByPrimaryKey(TProcTimeoutKey key);

    int updateByExampleSelective(@Param("record") TProcTimeout record, @Param("example") TProcTimeoutExample example);

    int updateByExample(@Param("record") TProcTimeout record, @Param("example") TProcTimeoutExample example);

    int updateByPrimaryKeySelective(TProcTimeout record);

    int updateByPrimaryKey(TProcTimeout record);
}