package org.jeff.javatool.tool.myactiviti.intfc;

import org.jeff.javatool.tool.myactiviti.entity.result.Result;

/**
 * 管理办公的所有定义
 * Created by weijianfu on 2017/1/23.
 */
public interface IWFDefOfficialService {

    /**
     * 办公-发文申请
     */
    public Result<Void> generateFaWen();

    /**
     * 办公-收文申请
     */
    public Result<Void> generateShouWen();

    /**
     * 办公-北京分公司部门收文
     */
    public Result<Void> generateShouWenBJFGS();

    /**
     * 办公-财务部部门收文
     */
    public Result<Void> generateShouWenCWB();

    /**
     * 办公-互联网部门收文
     */
    public Result<Void> generateShouWenHLW();

    /**
     * 办公-金融投资部部门收文
     */
    public Result<Void> generateShouWenJRTZB();

    /**
     * 办公-人力资源部门收文
     */
    public Result<Void> generateShouWenRLZY();

    /**
     * 办公-研发中心部门收文
     */
    public Result<Void> generateShouWenYFZX();

    /**
     * 办公-云计算事业群部门收文
     */
    public Result<Void> generateShouWenYJSSYQ();

    /**
     * 办公-云通信事业群部门收文
     */
    public Result<Void> generateShouWenYTXSYQ();

    /**
     * 办公-总裁办部门收文
     */
    public Result<Void> generateShouWenZCB();


    /**
     * 办公-新闻发布申请
     */
    public Result<Void> generateXinWenFaBu();

    /**
     * 办公-用印申请
     */
    public Result<Void> generateYongYin();

    /**
     * 督办
     */
    public Result<Void> generateDuBan();
}
