package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomExample;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomKey;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;

import java.util.List;

public interface TProcCustomMapper {

    /**
     * 查询符合条件的记录数
     */
    int countList(QueryCustomProcessCondition condition);

    /**
     * 查询符合条件的记录
     */
    List<TProcCustom> queryList(QueryCustomProcessCondition condition);

    int countByExample(TProcCustomExample example);

    int deleteByExample(TProcCustomExample example);

    int deleteByPrimaryKey(TProcCustomKey key);

    int insert(TProcCustom record);

    int insertSelective(TProcCustom record);

    List<TProcCustom> selectByExample(TProcCustomExample example);

    TProcCustom selectByPrimaryKey(TProcCustomKey key);

    int updateByExampleSelective(@Param("record") TProcCustom record, @Param("example") TProcCustomExample example);

    int updateByExample(@Param("record") TProcCustom record, @Param("example") TProcCustomExample example);

    int updateByPrimaryKeySelective(TProcCustom record);

    int updateByPrimaryKey(TProcCustom record);
}