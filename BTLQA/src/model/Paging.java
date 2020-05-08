package model;

import java.io.Serializable;

public class Paging implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer pageSize;
	private Integer beginIndex;

	public Paging() {
		this.pageSize = 2;
		this.beginIndex = 0;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

}
