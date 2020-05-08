package service.impl;

import java.util.List;

import dao.RetirementModeDao;
import dao.impl.RetirementModeDaoImpl;
import model.RetirementMode;
import service.RetirementModeService;

public class RetirementModeServiceImpl implements RetirementModeService {
	RetirementModeDao rmDao = new RetirementModeDaoImpl();

	@Override
	public void insert(RetirementMode retirementMode) {
		rmDao.insert(retirementMode);
	}

	@Override
	public void update(RetirementMode retirementMode) {
		RetirementMode old = rmDao.get(retirementMode.getId());
		if (old != null) {
			rmDao.update(retirementMode);
		}
	}

	@Override
	public void delete(int id) {
		RetirementMode old = rmDao.get(id);
		if (old != null) {
			rmDao.delete(id);
		}
	}

	@Override
	public RetirementMode get(int id) {
		return rmDao.get(id);
	}

	@Override
	public List<RetirementMode> getAll() {
		return rmDao.getAll();
	}
}
