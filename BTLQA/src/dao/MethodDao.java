package dao;

import java.util.List;

import model.Method;

public interface MethodDao {
	public void insert(Method method);

	public void update(Method method);

	public void delete(int id);

	public Method get(int id);

	public List<Method> getAll();
}
