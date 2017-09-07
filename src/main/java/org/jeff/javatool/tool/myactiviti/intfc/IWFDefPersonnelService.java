package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.result.Result;

/**
 * 管理人事的所有定义
 * Created by weijianfu on 2017/1/23.
 */
public interface IWFDefPersonnelService {

    /**
     * 人力-员工外勤申请
     */
    public Result<Void> generateChuChai();

    /**
     * 人力-辞职申请
     */
    public Result<Void> generateCiZhi();

    /**
     * 人力-离职审批流程
     */
    public Result<Void> generateLiZhi();

    /**
     * 人力-离职手续办理流程
     */
    public Result<Void> generateLiZhiJiaoJie();

    /**
     * 人力-员工请假申请
     */
    public Result<Void> generateQingJia();

    /**
     * 人力-入职审批流程
     */
    public Result<Void> generateRuZhi();

    /**
     * 人力-未打卡说明
     */
    public Result<Void> generateWeiDaKa();

    /**
     * 人力-职务异动审批流程
     */
    public Result<Void> generateZhiWuYiDong();

    /**
     * 人力-转正审批流程
     */
    public Result<Void> generateZhuanZheng();
}
