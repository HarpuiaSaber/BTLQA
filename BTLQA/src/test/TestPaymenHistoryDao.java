package test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.UserPaymentHistoryDao;
import dao.impl.UserPaymentHistoryDaoImpl;
import model.Search;
import model.UserPaymentHistory;
import utils.StringUtils;

public class TestPaymenHistoryDao {

	private UserPaymentHistoryDao uphDao = new UserPaymentHistoryDaoImpl();
	private List<UserPaymentHistory> uphList;
	private Search search;

	@Test
	public void search1() {
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
	public void search2() {
		// init
		String idDistrict = "339";
		search = new Search();
		search.setIdDistrict(idDistrict);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idDistrict, history.getInsurance().getUser().getVillage().getDistrict().getId());
		}
	}

	@Test
	public void search3() {
		// init
		String idVillage = "12658";
		search = new Search();
		search.setIdVillage(idVillage);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idVillage, history.getInsurance().getUser().getVillage().getId());
		}
	}

	@Test
	public void search4() {
		// init
		String idProvince = "34";
		String idDistrict = "339";
		search = new Search();
		search.setIdProvince(idProvince);
		search.setIdDistrict(idDistrict);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idProvince,
					history.getInsurance().getUser().getVillage().getDistrict().getProvince().getId());
			Assert.assertEquals(idDistrict, history.getInsurance().getUser().getVillage().getDistrict().getId());
		}
	}

	@Test
	public void search5() {
		// init
		String idProvince = "34";
		String idDistrict = "339";
		String idVillage = "12658";
		search = new Search();
		search.setIdVillage(idVillage);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idProvince,
					history.getInsurance().getUser().getVillage().getDistrict().getProvince().getId());
			Assert.assertEquals(idDistrict, history.getInsurance().getUser().getVillage().getDistrict().getId());
			Assert.assertEquals(idVillage, history.getInsurance().getUser().getVillage().getId());
		}
	}

	@Test
	public void search6() {
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
			Assert.assertEquals(name, history.getInsurance().getUser().getName());
		}
	}

	@Test
	public void search7() {
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
			Assert.assertEquals(identityCard, history.getInsurance().getUser().getIdentityCard());
		}
	}

	@Test
	public void search8() {
		// init
		Date dob = StringUtils.convertToDate("1998-01-07");
		search = new Search();
		search.setDob(dob);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(1, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertTrue(dob.equals(history.getInsurance().getUser().getDob()));
		}
	}

	@Test
	public void search9() {
		// init
		Date startDate = StringUtils.convertToDate("2020-03-05");
		search = new Search();
		search.setStartDate(startDate);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(4, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertTrue(startDate.before(history.getTime()) || startDate.equals(history.getTime()));
		}
	}

	@Test
	public void search10() {
		// init
		Date endDate = StringUtils.convertToDate("2020-06-05");
		search = new Search();
		search.setEndDate(endDate);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(4, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertTrue(endDate.after(history.getTime()) || endDate.equals(history.getTime()));
		}
	}

	@Test
	public void search11() {
		// init
		Date startDate = StringUtils.convertToDate("2020-03-05");
		Date endDate = StringUtils.convertToDate("2020-06-05");
		search = new Search();
		search.setStartDate(startDate);
		search.setEndDate(endDate);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(4, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertTrue(startDate.before(history.getTime()) || startDate.equals(history.getTime()));
			Assert.assertTrue(endDate.after(history.getTime()) || endDate.equals(history.getTime()));
		}
	}

	// exception

	@Test
	public void search12() {
		// init
		Date startDate = StringUtils.convertToDate("2020-06-05");
		Date endDate = StringUtils.convertToDate("2020-03-05");
		search = new Search();
		search.setStartDate(startDate);
		search.setEndDate(endDate);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(0, uphList.size());
	}

	@Test
	public void search13() {
		// init
		String name = "ko co ten";
		search = new Search();
		search.setName(name);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(0, uphList.size());
	}

	@Test
	public void search14() {
		// init
		Long identityCard = 12345678956L;
		search = new Search();
		search.setIdentityCard(identityCard);

		// get result by dao function
		uphList = uphDao.search(search);
		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(0, uphList.size());
	}

}
