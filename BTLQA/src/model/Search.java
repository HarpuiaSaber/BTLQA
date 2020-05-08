package model;

import java.io.Serializable;
import java.util.Date;

public class Search implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date startDate;
	private Date endDate;
	private String idProvince;
	private String idDistrict;
	private String idVillage;
	private String name;
	private Long identityCard;
	private Date dob;
	private Integer mode;

	public Search() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(String idProvince) {
		this.idProvince = idProvince;
	}

	public String getIdDistrict() {
		return idDistrict;
	}

	public void setIdDistrict(String idDistrict) {
		this.idDistrict = idDistrict;
	}

	public String getIdVillage() {
		return idVillage;
	}

	public void setIdVillage(String idVillage) {
		this.idVillage = idVillage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(Long identityCard) {
		this.identityCard = identityCard;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

}
