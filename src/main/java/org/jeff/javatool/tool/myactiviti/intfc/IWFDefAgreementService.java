package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.result.Result;

/**
 * 管理合同的所有定义
 * Created by weijianfu on 2017/1/23.
 */
public interface IWFDefAgreementService {

    /**
     * 合同-新增关联合同申请
     */
    public Result<Void> generateGuanLianHeTong();

    /**
     * 合同-新增合同模版申请
     */
    public Result<Void> generateHeTongMuBan();

    /**
     * 合同-新增单个合同申请
     */
    public Result<Void> generateHeTongtShenQing();

    /**
     * 合同-新增合同移交申请
     */
    public Result<Void> generateHeTongYiJiao();

}
