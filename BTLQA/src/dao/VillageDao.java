package dao;

import java.util.List;

import model.Village;

public interface VillageDao {
	public List<Village> getByDistrictId(String id);
}
