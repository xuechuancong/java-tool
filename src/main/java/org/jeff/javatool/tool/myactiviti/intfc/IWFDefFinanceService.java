package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.result.Result;

/**
 * 管理财务的所有定义
 * Created by weijianfu on 2017/1/23.
 */
public interface IWFDefFinanceService {

    /**
     * 财务-员工报账-差旅费报销申请
     */
    public Result<Void> generateBZChaiLvFei();

    /**
     * 财务-员工报账-借款申请
     */
    public Result<Void> generateBZGeRenJieKuan();

    /**
     * 财务-业务报账-付款申请
     */
    public Result<Void> generateBZYeWuFuKuan();

    /**
     * 财务-业务报账-借款申请
     */
    public Result<Void> generateBZYeWuJieKuan();

    /**
     * 财务-业务报账-已列账支付申请
     */
    public Result<Void> generateBZYiLieZhang();

    /**
     * 财务-员工报账-付款申请
     */
    public Result<Void> generateBZYuanGongFuKuan();

    /**
     * 财务-员工报账-还款申请
     */
    public Result<Void> generateBZYuanGongHuanKuan();

    /**
     * 财务-客商申请改状态
     */
    public Result<Void> generateKeShangGaiZhuangTai();

    /**
     * 财务-客商录入申请
     */
    public Result<Void> generateKeShangShenQing();

    /**
     * 财务-客商修改申请
     */
    public Result<Void> generateKeShangXiuGai();

    /**
     * 财务-事前审批-出差事前审批
     */
    public Result<Void> generateSQChuChai();

    /**
     * 财务-事前审批-会议类
     */
    public Result<Void> generateSQSPHuiYi();

    /**
     * 财务-事前审批-公务接待
     */
    public Result<Void> generateSQSPJieDai();

    /**
     * 财务-事前审批-培训类事前审批
     */
    public Result<Void> generateSQSPPeiXun();

    /**
     * 财务-事前审批-通用事前审批
     */
    public Result<Void> generateSQSPTongYong();


    /**
     * 暂收款管理-收款申请
     */
    public Result<Void> generateBZZanShouKuanShouKuan();

    /**
     * 收入到账核销申请
     */
    public Result<Void> generateBZDaoZhangHeXiao();

    /**
     * 暂收款管理-退款申请
     */
    public Result<Void> generateBZZanShouKuanTuiKuan();

    /**
     * 收入确认申请
     */
    public Result<Void> generateBZShouRuQueRen();

    /**
     * 收入开票申请
     */
    public Result<Void> generateBZShouRuKaiPiao();

    /**
     * 总账会计核算申请
     */
    public Result<Void> generateBZZongZhangHeSuan();

    /**
     * 费用预提管理-预提申请
     */
    public Result<Void> generateBZFeiYongYuTi();

    /**
     * 费用预提管理-冲销申请
     */
    public Result<Void> generateBZFeiYongChongXiao();


}
