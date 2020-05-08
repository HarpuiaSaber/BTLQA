package dao;

import java.util.List;

import model.District;

public interface DistrictDao {
	public List<District> getByProvinceId(String id);
}
