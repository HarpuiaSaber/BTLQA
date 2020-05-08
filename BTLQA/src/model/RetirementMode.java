package model;

import java.io.Serializable;

public class RetirementMode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer year;
	private String gender;
	private Integer old;
	private Double increase;
	private String time;
	private Double minPercent;
	private Double maxPercent;

	public RetirementMode() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getOld() {
		return old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public Double getIncrease() {
		return increase;
	}

	public void setIncrease(Double increase) {
		this.increase = increase;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getMinPercent() {
		return minPercent;
	}

	public void setMinPercent(Double minPercent) {
		this.minPercent = minPercent;
	}

	public Double getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(Double maxPercent) {
		this.maxPercent = maxPercent;
	}

}
