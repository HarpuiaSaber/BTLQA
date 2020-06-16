package test;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import dao.InsuranceDao;
import dao.UserPaymentHistoryDao;
import dao.impl.InsuranceDaoImpl;
import dao.impl.UserPaymentHistoryDaoImpl;
import model.Insurance;
import model.Search;
import model.UserPaymentHistory;
public class TestPaymenHistoryDao {

	private UserPaymentHistoryDao uphDao = new UserPaymentHistoryDaoImpl();
	private InsuranceDao insuranceDao = new InsuranceDaoImpl();
	private List<UserPaymentHistory> uphList;
	private List<Insurance> insurances;
	private Search search;
	

	@Test
	public void test1() throws NamingException {
		// init
		String idProvince = "36";
		search = new Search();
		search.setIdProvince(idProvince);

		// get result by dao function
		uphList = uphDao.search(search);
		insurances = insuranceDao.search(search);

		// test
		Assert.assertNotNull(uphList);
		Assert.assertEquals(2, uphList.size());
		for (UserPaymentHistory history : uphList) {
			Assert.assertEquals(idProvince,
					history.getInsurance().getUser().getVillage().getDistrict().getProvince().getId());
		}
	}
}
