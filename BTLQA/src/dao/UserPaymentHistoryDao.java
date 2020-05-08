package dao;

import java.util.List;

import model.Paging;
import model.Search;
import model.UserPaymentHistory;

public interface UserPaymentHistoryDao {
	
	public List<UserPaymentHistory> search(Search search);
	
	public List<UserPaymentHistory> searchWithPaging(Search search, Paging paging);

	public int getTotalRecord(Search search);
}
