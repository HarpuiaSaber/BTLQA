package service;

import java.util.List;

import model.District;

public interface DistrictService {
	public List<District> getByProvinceId(String id);
}
