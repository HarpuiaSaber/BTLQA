package service;

import java.util.List;

import model.RetirementMode;

public interface RetirementModeService {
	public void insert(RetirementMode retirementMode);

	public void update(RetirementMode retirementMode);

	public void delete(int id);

	public RetirementMode get(int id);

	public List<RetirementMode> getAll();
}
