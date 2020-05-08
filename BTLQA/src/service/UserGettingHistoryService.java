package service;

import java.util.List;

import model.Paging;
import model.Search;
import model.UserGettingHistory;

public interface UserGettingHistoryService {
	
	public List<UserGettingHistory> search(Search search);
	
	public List<UserGettingHistory> searchWithPaging(Search search, Paging paging);

	public int getTotalRecord(Search search);
	
	public String exportToExecl(Search search);
}
