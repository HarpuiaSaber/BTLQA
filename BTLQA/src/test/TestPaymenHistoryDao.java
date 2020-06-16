package test;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import dao.UserPaymentHistoryDao;
import dao.impl.UserPaymentHistoryDaoImpl;
import model.Search;
import model.UserPaymentHistory;
public class TestPaymenHistoryDao {

	private UserPaymentHistoryDao uphDao = new UserPaymentHistoryDaoImpl();
	private List<UserPaymentHistory> uphList;
	private Search search;
	

	@Test
	public void test1() {
		// init
		String name = "Nguyễn Quang Toàn";
		search = new Search();
		search.setName(name);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(name,
					history.getInsurance().getUser().getName());
		}
	}
}
