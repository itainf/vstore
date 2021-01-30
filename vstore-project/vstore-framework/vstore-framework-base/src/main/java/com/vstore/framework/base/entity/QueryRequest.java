package com.vstore.framework.base.entity;


import com.vstore.framework.base.util.MapUtils;

public class QueryRequest {

	private int pageNumber;

	private int pageSize;

	private String sortName;

	private String sortOrder;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = MapUtils.setAttrKey(sortName);
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}


	@Override
	public String toString() {
		return "QueryRequest [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", sortName=" + sortName
				+ ", sortOrder=" + sortOrder + "]";
	}



}
