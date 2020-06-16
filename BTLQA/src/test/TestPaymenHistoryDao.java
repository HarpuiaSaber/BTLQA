package test;

import java.util.List;

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
		String idProvince = "36";
		search = new Search();
		search.setIdProvince(idProvince);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(2, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idProvince,
					history.getInsurance().getUser().getVillage().getDistrict().getProvince().getId());
		}
	}
	
	@Test
	public void test2() {
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
	

	@Test
	public void test3() {
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
	
	@Test
	public void test4() {
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
	@Test
	public void test5() {
		// init
		Long identityCard = 152225247L;
		search = new Search();
		search.setIdentityCard(identityCard);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(identityCard,
					history.getInsurance().getUser().getIdentityCard());
		}
	}
	@Test
	public void test6() {
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
	@Test
	public void test7() {
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
	@Test
	public void test8() {
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
	@Test
	public void test9() {
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
	@Test
	public void test10() {
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
