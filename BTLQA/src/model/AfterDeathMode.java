package model;

import java.io.Serializable;

public class AfterDeathMode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String status;
	private Double month;
	private String time;
	private Double reduction;

	public AfterDeathMode() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getMonth() {
		return month;
	}

	public void setMonth(Double month) {
		this.month = month;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getReduction() {
		return reduction;
	}

	public void setReduction(Double reduction) {
		this.reduction = reduction;
	}

}
