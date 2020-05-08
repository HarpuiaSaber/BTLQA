package service.impl;

import java.util.List;

import dao.MethodDao;
import dao.impl.MethodDaoImpl;
import model.Method;
import service.MethodService;

public class MethodServiceImpl implements MethodService {

	private MethodDao methodDao = new MethodDaoImpl();

	@Override
	public void insert(Method method) {
		methodDao.insert(method);
	}

	@Override
	public void update(Method method) {
		Method old = methodDao.get(method.getId());
		if (old != null) {
		methodDao.update(method);}
	}

	@Override
	public void delete(int id) {
		Method old = methodDao.get(id);
		if (old != null) {
			methodDao.delete(id);
		}
	}

	@Override
	public Method get(int id) {
		return methodDao.get(id);
	}

	@Override
	public List<Method> getAll() {
		return methodDao.getAll();
	}

}
