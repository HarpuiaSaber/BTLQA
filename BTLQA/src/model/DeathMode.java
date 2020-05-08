package model;

import java.io.Serializable;

public class DeathMode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double baseSalary;
	private Double coefficient;
	private Double year;

	public DeathMode() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Double getYear() {
		return year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

}
