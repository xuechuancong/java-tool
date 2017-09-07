package org.jeff.javatool.tool.myactiviti.domain;

import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessListPage;
import org.jeff.javatool.tool.myactiviti.entity.result.TaskShowListVo;

import java.util.List;

public interface OldProcessListMapper {
	List<TaskShowListVo> getWaittingDeal(QueryProcessListPage queryProcessListPage);

	int countWaittingDeal(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getWaittingDealLike(QueryProcessListPage queryProcessListPage);

	int countWaittingDealLike(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getAreadyDeal(QueryProcessListPage queryProcessListPage);

	int countAreadyDeal(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getAreadyDealLike(QueryProcessListPage queryProcessListPage);

	int countAreadyDealLike(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getRequestDo(QueryProcessListPage queryProcessListPage);

	int countRequestDo(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getRequestDoLike(QueryProcessListPage queryProcessListPage);

	int countRequestDoLike(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getRequestDone(QueryProcessListPage queryProcessListPage);

	int countRequestDone(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getRequestDoneLike(QueryProcessListPage queryProcessListPage);

	int countRequestDoneLike(QueryProcessListPage queryProcessListPage);
	List<TaskShowListVo> getMyDraft(QueryProcessListPage queryProcessListPage);
	
	int countMyDraft(QueryProcessListPage queryProcessListPage);
	
	List<TaskShowListVo> getMyDraftLike(QueryProcessListPage queryProcessListPage);
	
	int countMyDraftLike(QueryProcessListPage queryProcessListPage);
	List<TaskShowListVo> getProcDefList(QueryProcessListPage queryProcessListPage);
	
	int countProcDefList(QueryProcessListPage queryProcessListPage);
	
	List<TaskShowListVo> getProcDefListLike(QueryProcessListPage queryProcessListPage);
	
	int countProcDefListLike(QueryProcessListPage queryProcessListPage);

}
