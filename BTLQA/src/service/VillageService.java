package service;

import java.util.List;

import model.Village;

public interface VillageService {
	public List<Village> getByDistrictId(String id);

}
