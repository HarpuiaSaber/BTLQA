package service;

import java.util.List;

import model.AfterDeathMode;

public interface AfterDeathModeService {
	public void insert(AfterDeathMode afterDeathMode);

	public void update(AfterDeathMode afterDeathMode);

	public void delete(int id);

	public AfterDeathMode get(int id);

	public List<AfterDeathMode> getAll();
}
