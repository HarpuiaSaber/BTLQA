package service;

import java.util.List;

import model.Paging;
import model.Search;
import model.UserPaymentHistory;

public interface UserPaymentHistoryService {
	
	public List<UserPaymentHistory> search(Search search);

	public List<UserPaymentHistory> searchWithPaging(Search search, Paging paging);

	public int getTotalRecord(Search search);
	
	public String exportToExecl(Search search);
}
