package dao;

import java.util.List;

import model.AfterDeathMode;

public interface AfterDeathModeDao {
	public void insert(AfterDeathMode afterDeathMode);

	public void update(AfterDeathMode afterDeathMode);

	public void delete(int id);

	public AfterDeathMode get(int id);

	public List<AfterDeathMode> getAll();
}
