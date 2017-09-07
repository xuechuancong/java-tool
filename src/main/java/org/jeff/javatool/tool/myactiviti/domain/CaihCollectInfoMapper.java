package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.CaihCollectInfo;
import org.jeff.javatool.tool.myactiviti.domain.entity.CaihCollectInfoExample;

import java.util.List;

public interface CaihCollectInfoMapper {
    int countByExample(CaihCollectInfoExample example);

    int deleteByExample(CaihCollectInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CaihCollectInfo record);

    int insertSelective(CaihCollectInfo record);

    List<CaihCollectInfo> selectByExample(CaihCollectInfoExample example);

    CaihCollectInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CaihCollectInfo record, @Param("example") CaihCollectInfoExample example);

    int updateByExample(@Param("record") CaihCollectInfo record, @Param("example") CaihCollectInfoExample example);

    int updateByPrimaryKeySelective(CaihCollectInfo record);

    int updateByPrimaryKey(CaihCollectInfo record);
}