package dao;

import java.util.List;

import model.RetirementMode;

public interface RetirementModeDao {
	public void insert(RetirementMode retirementMode);

	public void update(RetirementMode retirementMode);

	public void delete(int id);

	public RetirementMode get(int id);

	public List<RetirementMode> getAll();
}
