package org.jeff.javatool.tool.myactiviti.process;

import org.jeff.javatool.tool.myactiviti.config.CustomProcessState;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessState;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcCustomService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
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
 * 测试自定义流程
 * Created by weijianfu on 2017/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestProcCustom {


    @Autowired
    private IWFProcCustomService procCustomService;

    @Test
    @Rollback(true)
    public void testInsertAndUpdate() throws Exception {
        //录入
        TProcCustom procCustom2 = new TProcCustom();
        procCustom2.setProcDefKey(null);
        procCustom2.setProcDefName("ProcDefName2");
        procCustom2.setFormId("FormId2");
        procCustom2.setProcDefCategory("ProcDefCategory2");
        procCustom2.setJson("Json2");
        procCustom2.setCreator("Creator2");
        Result<Integer> insert = procCustomService.insert(procCustom2, null);
        Assert.assertTrue(insert.success());

        //查询
        QueryCustomProcessCondition condition2 = new QueryCustomProcessCondition();
        condition2.setLimit(100);
        condition2.setOffset(0);
        condition2.setProcDefKey(procCustom2.getProcDefKey());
        Result<Pager<TProcCustom>> result2 = procCustomService.queryList(condition2, null);
        List<TProcCustom> datas2 = result2.getValue().getDatas();
        TProcCustom tProcCustomQuery2 = datas2.get(0);
        Assert.assertTrue(tProcCustomQuery2.getProcDefName().equals(procCustom2.getProcDefName()));
        Assert.assertTrue(datas2.size() == 1);

        //录入
        TProcCustom procCustom1 = new TProcCustom();
        procCustom1.setProcDefKey(null);
        procCustom1.setProcDefName("ProcDefName1");
        procCustom1.setFormId("FormId1");
        procCustom1.setProcDefCategory("ProcDefCategory1");
        procCustom1.setJson("Json1");
        procCustom1.setCreator("Creator1");
        Result<Integer> insert1 = procCustomService.insert(procCustom1, null);
        Assert.assertTrue(insert1.success());

        //更新
        procCustom2.setProcDefName("ProcDefNameC");
        Result<Integer> update = procCustomService.update(procCustom2, null);
        Assert.assertTrue(update.success());
        condition2.setProcDefKey(procCustom2.getProcDefKey());
        Result<Pager<TProcCustom>> result3 = procCustomService.queryList(condition2, null);
        List<TProcCustom> datas3 = result3.getValue().getDatas();
        TProcCustom tProcCustomQuery3 = datas3.get(0);
        Assert.assertTrue(tProcCustomQuery3.getProcDefName().equals("ProcDefNameC"));

        //更新为删除状态后，无法通过查询接口查询
        TCustomProcessState processState = new TCustomProcessState();
        processState.setProcDefKey(procCustom2.getProcDefKey());
        processState.setVersion(procCustom2.getVersion());
        processState.setState(CustomProcessState.DELETED.getState());
        procCustomService.updateState(processState, null);
        condition2.setProcDefKey(procCustom2.getProcDefKey());
        Result<Pager<TProcCustom>> result4 = procCustomService.queryList(condition2, null);
        Assert.assertTrue(result4.getValue().getDatas() == null || result4.getValue().getDatas().size() == 0);


        //激活后的信息，无法删除
        //生成的定义反序列化信息与自定义信息一致
    }

    @Test
    public void testSer() throws Exception {
        String json = "{\"procDefKey\":null,\"procDefName\":\"5434123\",\"procDescription\":\"12342134\",\"procDefCategory\":\"35\",\"formId\":\"1030_4_917\",\"formName\":\"\",\"formDefKey\":null,\"creator\":\"\",\"version\":\"\",\"tasks\":{\"startEvent\":{\"defKey\":\"startEvent\",\"defName\":\"开始\",\"defCategory\":null,\"roleIds\":null,\"roleNames\":null,\"organizationIds\":null,\"organizationNames\":null,\"haveOpinionText\":null,\"canEditPage\":null,\"haveAddSign\":null,\"haveDelegate\":null,\"canReadAtt\":null,\"canDownloadAtt\":null,\"canUploadAtt\":null,\"canUpdateMyAtt\":null,\"canUpdateOtherAtt\":null,\"canDeleteMyAtt\":null,\"canDeleteOtherAtt\":null,\"canAppEdit\":null,\"organizationIdsList\":[],\"roleIdsList\":[]},\"endEvent\":{\"defKey\":\"endEvent\",\"defName\":\"结束\",\"defCategory\":null,\"roleIds\":null,\"roleNames\":null,\"organizationIds\":null,\"organizationNames\":null,\"haveOpinionText\":null,\"canEditPage\":null,\"haveAddSign\":null,\"haveDelegate\":null,\"canReadAtt\":null,\"canDownloadAtt\":null,\"canUploadAtt\":null,\"canUpdateMyAtt\":null,\"canUpdateOtherAtt\":null,\"canDeleteMyAtt\":null,\"canDeleteOtherAtt\":null,\"canAppEdit\":null,\"organizationIdsList\":[],\"roleIdsList\":[]}},\"flows\":null,\"customVariable\":null,\"mapCurKey2Flows\":{},\"mapTarKey2Flows\":{}}\n";

        String json1 = "{\"procDefKey\":null,\"procDefName\":\"5434123\",\"procDescription\":\"12342134\",\"procDefCategory\":\"35\",\"formId\":\"1030_4_917\",\"formName\":\"\",\"formDefKey\":null,\"creator\":\"\",\"version\":\"\",\"tasks\":null,\"flows\":null,\"customVariable\":null,\"mapCurKey2Flows\":{},\"mapTarKey2Flows\":{}}";

        TCustomProcess deserialize = JsonUtil.deserialize(json, TCustomProcess.class);

        TCustomProcess deserialize1 = JsonUtil.deserialize(json1, TCustomProcess.class);

        Assert.assertTrue(deserialize != null);
        Assert.assertTrue(deserialize1 != null);
    }

    @Test
    public void testQueryList() {
        QueryCustomProcessCondition condition = new QueryCustomProcessCondition();
        condition.setLimit(1);
        condition.setOffset(0);

        Result<Pager<TProcCustom>> result = procCustomService.queryList(condition, null);

        Pager<TProcCustom> value = result.getValue();
        Assert.assertTrue(value == null || value.getDatas() == null || value.getDatas().size() <= 1);
    }

}
