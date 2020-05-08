package service.impl;

import java.util.List;

import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import model.Province;
import service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {
	private static List<Province> provinces;
	private ProvinceDao provinceDao = new ProvinceDaoImpl();

	@Override
	public List<Province> getAll() {
		if (provinces == null) {
			provinces = provinceDao.getAll();
		}
		return provinces;
	}
}
