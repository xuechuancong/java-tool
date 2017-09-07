package org.jeff.javatool.tool.myactiviti.domain;

import org.apache.ibatis.annotations.Param;

/**
 * Created by weijianfu on 2017/3/22.
 */
public interface CommonSeqMapper {

    long selectSeqByName(@Param("seqName") String seqName);
}
