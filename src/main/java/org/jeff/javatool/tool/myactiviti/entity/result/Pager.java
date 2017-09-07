package org.jeff.javatool.tool.myactiviti.entity.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weijianfu on 2016/11/22.
 */
public class Pager<T> implements Serializable {
    private int offset;
    private int limit;
    private int totalCount;
    private List<T> datas;

    public Pager(int offset, int limit, int totalCount, List<T> datas) {
        this.offset = offset;
        this.limit = limit;
        this.totalCount = totalCount;
        this.datas = datas;
    }

    public PageInfo buildPageInfo(){
        PageInfo pageInfo = new PageInfo();

        pageInfo.setCurrPage(this.getCurrPage());
        pageInfo.setPageSize(this.getLimit());
        pageInfo.setTotalCount(this.getTotalCount());

        return pageInfo;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getCurrPage() {
        if (limit == 0) {
            return 0;
        }
        return 1 + (offset / limit);
    }

    public int getTotalPage() {
	    if (limit == 0) {
		    return 0;
	    }
        return totalCount / limit + (totalCount % limit > 0 ? 1 : 0);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getDatas() {
        return datas;
    }
}
