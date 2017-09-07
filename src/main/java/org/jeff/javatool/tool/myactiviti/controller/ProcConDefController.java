package org.jeff.javatool.tool.myactiviti.controller;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.domain.entity.ProcConDef;
import org.jeff.javatool.tool.myactiviti.domain.vo.ProcConDefVo;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcConDefCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.ControllerRequestParam;
import org.jeff.javatool.tool.myactiviti.entity.result.JsonData;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IProcConDefService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/process/condef")
public class ProcConDefController {
	 private Log log = LogFactory.getLog(this.getClass());
	 @Autowired
	 private IProcConDefService iProcConDefService;

	 /**
	     * 生成/更新自定义流程常量
	     *
	     * @param param
	     * @return 
	     */
	    @RequestMapping(value = "", method = RequestMethod.POST)
	    @ResponseBody
	    public JsonData<String> replace(@RequestBody ControllerRequestParam<ProcConDefVo> param) {
	        try {
	        	ProcConDefVo vo = param.getData();
	            if (vo == null ) {
	                return JsonData.fail("参数为空");
	            }
	           
	            ProcConDef procConDef = new ProcConDef();
	            BeanUtils.copyProperties(vo, procConDef);

	            if (!StringUtils.isEmpty(procConDef.getId())) {// 更新
	                Result<Integer> update = iProcConDefService.update(procConDef, null);
	                if (update.error()) {
	                    return JsonData.fail(update.getDetail());
	                }
	            } else {//录入
	                Result<Integer> insert = iProcConDefService.insert(procConDef, null);
	                if (insert.error()) {
	                    return JsonData.fail(insert.getDetail());
	                }
	            }
	            return JsonData.success(procConDef.getId(), "成功");
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            return JsonData.fail(e.getMessage());
	        }
	    }
	    
	 /**
	     * 自定义流程常量状态修改
	     *
	     * @param param
	     * @return 
	     */
	    @RequestMapping(value = "state", method = RequestMethod.POST)
	    @ResponseBody
	    public JsonData<Void> state(@RequestBody ControllerRequestParam<ProcConDefVo> param) {
	        try {
	        	ProcConDefVo vo = param.getData();
	            if (vo == null
	                    || vo.getId() == null
	                    || vo.getId().length() <= 0
	                    || vo.getState() == null
	                    || vo.getState().length() <= 0) {
	                return JsonData.fail("参数为空");
	            }
	            ProcConDef procConDef = new ProcConDef();
	            procConDef.setId(vo.getId());
	            procConDef.setState(vo.getState());
	            Result<Integer> update = iProcConDefService.update(procConDef, null);
	            if (update.error()) {
	                return JsonData.fail(update.getDetail());
	            }
	            return JsonData.success("成功");
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            return JsonData.fail(e.getMessage());
	        }
	    }
	    
	    /**
	     * 自定义流程常量查询
	     *
	     * @param param
	     * @return 自定义流程常量集合
	     */
	    @RequestMapping(value = "list", method = RequestMethod.POST)
	    @ResponseBody
	    public JsonData<Pager<ProcConDefVo>> list(@RequestBody ControllerRequestParam<QueryProcConDefCondition> param) {
	    		    	
	    	try {
	    		QueryProcConDefCondition condition = param.getData();
	            Result<Pager<ProcConDef>> procConDefListResult = iProcConDefService.queryList(condition, null);
	            if (procConDefListResult.error()) {
	                return JsonData.fail(procConDefListResult.getDetail());
	            }
	            Pager<ProcConDef> procConDefPager = procConDefListResult.getValue();
	            List<ProcConDef> procConDefList = procConDefPager.getDatas();
	            List<ProcConDefVo> jsonList = Lists.newArrayList();
	            if(procConDefList == null || procConDefList.size() <= 0){
	                return JsonData.success(new Pager<ProcConDefVo>(procConDefPager.getOffset(), procConDefPager.getLimit(), procConDefPager.getTotalCount(), jsonList), "成功");
	            }	            
				for (ProcConDef data : procConDefList) {
					ProcConDefVo vo = new ProcConDefVo();
					BeanUtils.copyProperties(data, vo);
					jsonList.add(vo);
				}
	            return JsonData.success(new Pager<>(procConDefPager.getOffset(), procConDefPager.getLimit(), procConDefPager.getTotalCount(), jsonList), "成功");
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            return JsonData.fail(e.getMessage());
	        }
	    	
	    }
	    
//	    /**
//	     * 数据池获取下级组织信息
//	     * @param controllerRequestParam
//	     * @param request
//	     * @return
//	     */
//	    @RequestMapping(value="/getOfficeByPid",method=RequestMethod.POST)
//	    @ResponseBody
//	    public JsonData<List<OfficeInfoVO>> getOfficeByPid(@RequestBody ControllerRequestParam<DPOfficePidQueryVO> controllerRequestParam,
//		    	HttpServletRequest request){
//	    	JsonData<List<OfficeInfoVO>> jsonData = new JsonData<List<OfficeInfoVO>>();
//			try {
//				if (controllerRequestParam.getData() != null) {
//                    String pid = controllerRequestParam.getData().getPid();
//					List<OfficeInfoVO> voList = getOfficeByPid(pid);
//
//					jsonData.setResult(JsonData.Result.SUCCESS);
//					jsonData.setData(voList);
//					jsonData.setMessage("获取数据池组织信息成功");
//				} else {
//					jsonData.setResult(JsonData.Result.FAILED);
//					jsonData.setMessage("获取数据池组织信息，data不能为空");
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error(e.getMessage(), e);
//				jsonData.setResult(JsonData.Result.FAILED);
//				jsonData.setMessage(e.getMessage());
//			}
//			return jsonData;
//	    }

//		private List<OfficeInfoVO> getOfficeByPid(String pid) throws Exception {
//			DPOfficePidQueryVO query = new DPOfficePidQueryVO();
//			query.setPid(pid);
//			List<OfficeInfoVO> result = null;
//			List<DPOfficeInfoVO> temp = dataPoolService.selectOfficeByPid(query);
//			// DPOfficeInfoVO >> OfficeInfoVO
//			if (!CollectionUtils.isEmpty(temp)) {
//				result = new ArrayList<OfficeInfoVO>();
//				for (DPOfficeInfoVO vo : temp) {
//					OfficeInfoVO target = new OfficeInfoVO();
//					BeanUtils.copyProperties(vo, target);
//					result.add(target);
//				}
//			}
//
//			if (!CollectionUtils.isEmpty(result)) {
//				for (OfficeInfoVO el : result) {
//					//递归查询下属组织
//					List<OfficeInfoVO> children = getOfficeByPid(el.getId());
//					if (!CollectionUtils.isEmpty(children)) {
//						el.setChildren(children);
//					}
//				}
//			}
//			return result;
//		}
}
