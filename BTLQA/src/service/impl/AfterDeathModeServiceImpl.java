package service.impl;

import java.util.List;

import dao.AfterDeathModeDao;
import dao.impl.AfterDeathModeDaoImpl;
import model.AfterDeathMode;
import service.AfterDeathModeService;

public class AfterDeathModeServiceImpl implements AfterDeathModeService {

	AfterDeathModeDao admDao = new AfterDeathModeDaoImpl();

	@Override
	public void insert(AfterDeathMode afterDeathMode) {
		admDao.insert(afterDeathMode);
	}

	@Override
	public void update(AfterDeathMode afterDeathMode) {
		AfterDeathMode old = admDao.get(afterDeathMode.getId());
		if (old != null) {
			admDao.update(afterDeathMode);
		}
	}

	@Override
	public void delete(int id) {
		AfterDeathMode old = admDao.get(id);
		if (old != null) {
			admDao.delete(id);
		}
	}

	@Override
	public AfterDeathMode get(int id) {
		return admDao.get(id);
	}

	@Override
	public List<AfterDeathMode> getAll() {
		return admDao.getAll();
	}

}
