package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcAtt;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcAttExample;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcAttKey;

import java.util.List;

public interface TProcAttMapper {
    int countByExample(TProcAttExample example);

    int deleteByExample(TProcAttExample example);

    int deleteByPrimaryKey(TProcAttKey key);

    int insert(TProcAtt record);

    int insertSelective(TProcAtt record);

    List<TProcAtt> selectByExample(TProcAttExample example);

    TProcAtt selectByPrimaryKey(TProcAttKey key);

    int updateByExampleSelective(@Param("record") TProcAtt record, @Param("example") TProcAttExample example);

    int updateByExample(@Param("record") TProcAtt record, @Param("example") TProcAttExample example);

    int updateByPrimaryKeySelective(TProcAtt record);

    int updateByPrimaryKey(TProcAtt record);
}