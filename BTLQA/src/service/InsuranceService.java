package service;

import java.util.List;

import model.Insurance;
import model.Paging;
import model.Search;

public interface InsuranceService {

	public List<Insurance> search(Search search);

	public List<Insurance> searchWithPaging(Search search, Paging paging);

	public int getTotalRecord(Search search);
	
	public String exportToExecl(Search search);
}
