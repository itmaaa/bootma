package cn.maaa.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author bootdo 1992lcg@163.com
 */
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;
	
	//查询起始位
	private int offset = 0;
	// 每页条数
	private int limit = 10;
	//当前页数
	private int page = offset/limit+1;
	

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
