package dao;

import java.util.List;

import model.DeathMode;

public interface DeathModeDao {
	public void insert(DeathMode deathMode);

	public void update(DeathMode deathMode);

	public void delete(int id);

	public DeathMode get(int id);

	public List<DeathMode> getAll();
}
