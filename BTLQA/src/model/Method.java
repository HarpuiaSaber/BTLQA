package model;

import java.io.Serializable;

public class Method implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer month;

	public Method() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}