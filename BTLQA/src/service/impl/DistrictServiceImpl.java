package service.impl;

import java.util.List;

import dao.DistrictDao;
import dao.impl.DistrictDaoImpl;
import model.District;
import service.DistrictService;

public class DistrictServiceImpl implements DistrictService {
	private DistrictDao districtDao = new DistrictDaoImpl();

	@Override
	public List<District> getByProvinceId(String id) {
		return districtDao.getByProvinceId(id);
	}
}
