package model;

import java.io.Serializable;

public class Village implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private District district;

	public Village() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
