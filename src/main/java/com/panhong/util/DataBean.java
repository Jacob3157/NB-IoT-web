package com.panhong.util;

import java.util.List;

public class DataBean {
	private List rows;// 要返回的某一页的记录列表
	private int total; // 总记录数
	
	
	
	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


}
