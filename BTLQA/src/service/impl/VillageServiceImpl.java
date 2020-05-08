package service.impl;

import java.util.List;

import dao.VillageDao;
import dao.impl.VillageDaoImpl;
import model.Village;
import service.VillageService;

public class VillageServiceImpl implements VillageService {
	private VillageDao villageDao = new VillageDaoImpl();

	@Override
	public List<Village> getByDistrictId(String id) {		
		return villageDao.getByDistrictId(id);
	}
}
