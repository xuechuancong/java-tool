package org.jeff.javatool.tool.myactiviti.domain;

import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessListPage;
import org.jeff.javatool.tool.myactiviti.entity.result.TaskShowListVo;

import java.util.List;

public interface OldMesseageListMapper {
	List<TaskShowListVo> getUnReadMsg(QueryProcessListPage queryProcessListPage);

	int countUnReadMsg(QueryProcessListPage queryProcessListPage);

	List<TaskShowListVo> getUnReadMsgLike(QueryProcessListPage queryProcessListPage);

	int countUnReadMsgLike(QueryProcessListPage queryProcessListPage);
	List<TaskShowListVo> getAreadyReadMsg(QueryProcessListPage queryProcessListPage);
	
	int countAreadyReadMsg(QueryProcessListPage queryProcessListPage);
	
	List<TaskShowListVo> getAreadyReadMsgLike(QueryProcessListPage queryProcessListPage);
	
	int countAreadyReadMsgLike(QueryProcessListPage queryProcessListPage);
}
