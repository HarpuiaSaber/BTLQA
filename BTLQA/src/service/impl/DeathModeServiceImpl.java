package service.impl;

import java.util.List;

import dao.DeathModeDao;
import dao.impl.DeathModeDaoImpl;
import model.DeathMode;
import service.DeathModeService;

public class DeathModeServiceImpl implements DeathModeService {

	private DeathModeDao dmDao = new DeathModeDaoImpl();

	@Override
	public void insert(DeathMode deathMode) {
		dmDao.insert(deathMode);
	}

	@Override
	public void update(DeathMode deathMode) {
		DeathMode old = dmDao.get(deathMode.getId());
		if (old != null) {
			dmDao.update(deathMode);
		}
	}

	@Override
	public void delete(int id) {
		DeathMode old = dmDao.get(id);
		if (old != null) {
			dmDao.delete(id);
		}
	}

	@Override
	public DeathMode get(int id) {
		return dmDao.get(id);
	}

	@Override
	public List<DeathMode> getAll() {
		return dmDao.getAll();
	}

}
