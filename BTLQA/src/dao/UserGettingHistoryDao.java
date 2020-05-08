package dao;

import java.util.List;

import model.Paging;
import model.Search;
import model.UserGettingHistory;

public interface UserGettingHistoryDao {
	
	public List<UserGettingHistory> search(Search search);

	public List<UserGettingHistory> searchWithPaging(Search search, Paging paging);

	public int getTotalRecord(Search search);
}
