package org.jeff.javatool.tool.myactiviti.process;

import org.jeff.javatool.tool.myactiviti.config.ProcConDefType;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.config.TaskOperation;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDef;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcConDefCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IProcConDefService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 测试自定义流程常量
 * Created by weijianfu on 2017/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestProcConDef {


    @Autowired
    private IProcConDefService procConDefService;


    @Test
    @Rollback(true)
    public void testInsertAndUpdate() throws Exception {
        ProcConDef procConDef = new ProcConDef();
        procConDef.setVal("测试");
        procConDef.setDataType(ProcConDefType.PROC_DEF_CATEGORY.getType());
        procConDef.setShowOrder(1L);
        procConDef.setState("1");

        procConDefService.insert(procConDef, null);

        QueryProcConDefCondition condition = new QueryProcConDefCondition();
        condition.setLimit(1);
        condition.setOffset(0);
        condition.setId(procConDef.getId());

        Result<Pager<ProcConDef>> result = procConDefService.queryList(condition, null);
        Assert.assertTrue(result.getValue().getDatas().get(0).getVal().equals("测试"));
        Assert.assertTrue(result.getValue().getDatas().get(0).getState().equals("1"));

        procConDef.setVal("测试1");
        procConDefService.update(procConDef, null);

        Result<Pager<ProcConDef>> result1 = procConDefService.queryList(condition, null);
        Assert.assertTrue(result1.getValue().getDatas().get(0).getVal().equals("测试1"));
        Assert.assertTrue(result1.getValue().getDatas().get(0).getState().equals("1"));
    }

    @Test
    public void testQueryList() throws Exception {
        //测试分页
        QueryProcConDefCondition condition = new QueryProcConDefCondition();
        condition.setLimit(1);
        condition.setOffset(0);

        Result<Pager<ProcConDef>> result = procConDefService.queryList(condition, null);

        Pager<ProcConDef> value = result.getValue();
        Assert.assertTrue(value == null || value.getDatas() == null || value.getDatas().size() <= 1);

        //测试任务定义类型，固定值，不允许新增、修改
        condition.setLimit(Integer.MAX_VALUE);
        condition.setOffset(0);
        condition.setState("1");
        condition.setDataType(ProcConDefType.TASK_DEF_CATEGORY.getType());

        Result<Pager<ProcConDef>> result1 = procConDefService.queryList(condition, null);
        List<ProcConDef> datas = result1.getValue().getDatas();
        Assert.assertTrue(datas.size() == 3);
        for (ProcConDef data : datas) {
            if (!TaskCategory.NORMAL.getCategory().equals(data.getId())
                    && !TaskCategory.COUNTER_SIGN.getCategory().equals(data.getId())
                    && !TaskCategory.PARALLEL_SIGN.getCategory().equals(data.getId())) {
                throw new Exception("任务定义类型错误，ID：" + data.getId());
            }
        }

        //测试流向类型类型，固定值，允许新增、不允许修改
        condition.setLimit(Integer.MAX_VALUE);
        condition.setOffset(0);
        condition.setState("1");
        condition.setDataType(ProcConDefType.FLOW_TYPE.getType());

        Result<Pager<ProcConDef>> result2 = procConDefService.queryList(condition, null);
        List<ProcConDef> datas1 = result2.getValue().getDatas();
        Assert.assertTrue(datas1.size() >= 4);
        boolean haveAgree = false;
        for (ProcConDef data : datas1) {
            if (TaskOperation.AGREE.getOperation().equals(data.getId()) && "同意".equals(data.getVal())) {
                haveAgree = true;
            }
        }
        Assert.assertTrue(haveAgree);//一定要有同意，并且值为1，并行审签逻辑必须
    }

}
