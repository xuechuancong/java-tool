package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;
import org.jeff.javatool.tool.myactiviti.domain.entity.CaihAuthorizeManage;
import org.jeff.javatool.tool.myactiviti.domain.entity.CaihAuthorizeManageExample;

import java.util.List;

public interface CaihAuthorizeManageMapper {
    int countByExample(CaihAuthorizeManageExample example);

    int deleteByExample(CaihAuthorizeManageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CaihAuthorizeManage record);

    int insertSelective(CaihAuthorizeManage record);

    List<CaihAuthorizeManage> selectByExample(CaihAuthorizeManageExample example);

    CaihAuthorizeManage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CaihAuthorizeManage record, @Param("example") CaihAuthorizeManageExample example);

    int updateByExample(@Param("record") CaihAuthorizeManage record, @Param("example") CaihAuthorizeManageExample example);

    int updateByPrimaryKeySelective(CaihAuthorizeManage record);

    int updateByPrimaryKey(CaihAuthorizeManage record);
}