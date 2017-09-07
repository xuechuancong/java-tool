package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by weijianfu on 2017/3/22.
 */
@Mapper
public interface ActivitiMapper {

    @Update("update act_hi_taskinst set PROC_DEF_ID_ = #{procDefId} , PROC_INST_ID_ = #{procInstId} where 1=1 and ID_ in (#{ids})")
    void updateHiTaskInst(@Param("procDefId") String procDefId,
                          @Param("procInstId") String procInstId,
                          @Param("ids") String ids);
}
