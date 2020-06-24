package test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.InsuranceDao;
import dao.impl.InsuranceDaoImpl;
import model.Insurance;
import model.Search;
import utils.StringUtils;

public class TestInsuranceDao {
	private InsuranceDao insuranceDao = new InsuranceDaoImpl();
	private List<Insurance> insurances;
	private Search search;
	@Test
	public void search1() {
		// init
		String idProvince = "26"; //26 Vinh Phúc
		search = new Search();
		search.setIdProvince(idProvince);

		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(idProvince, insurance.getUser().getVillage().getDistrict().getProvince().getId());
		}
	}
	@Test
	public void search2() {
		// init
		String idDistrict = "247"; //247 huyện Tam Dương
		search = new Search();
		search.setIdDistrict(idDistrict);

		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(idDistrict, insurance.getUser().getVillage().getDistrict().getId());
		}
	}
	
	@Test
	public void search3() {
		// init
		String idVillage = "08869"; //Thi tran Hop Hoa
		search = new Search();
		search.setIdVillage(idVillage);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(idVillage, insurance.getUser().getVillage().getId());
		}
	}
	@Test
	public void search4() {
		// init
		String idProvince = "26";  
		String idDistrict = "247";
		search = new Search();
		search.setIdProvince(idProvince);
		search.setIdDistrict(idDistrict);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(idProvince, insurance.getUser().getVillage().getDistrict().getProvince().getId());
			Assert.assertEquals(idDistrict, insurance.getUser().getVillage().getDistrict().getId());
		}
	}
	@Test
	public void search5() {
		// init
		String idProvince = "26";  
		String idDistrict = "247";
		String idVillage = "08869";
		search = new Search();
		search.setIdProvince(idProvince);
		search.setIdDistrict(idDistrict);
		search.setIdVillage(idVillage);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(idProvince, insurance.getUser().getVillage().getDistrict().getProvince().getId());
			Assert.assertEquals(idDistrict, insurance.getUser().getVillage().getDistrict().getId());
			Assert.assertEquals(idVillage, insurance.getUser().getVillage().getId());
		}
	}
	
	@Test
	public void search6() {
		// init
		String name = "Nguyễn Xuân Thụy"; 
		search = new Search();
		search.setName(name);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(name, insurance.getUser().getName());
		}
	}
	
	@Test
	public void search7() {
		// init
		Long identityCard = 123456789L;
		search = new Search();
		search.setIdentityCard(identityCard);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(identityCard, insurance.getUser().getIdentityCard());
		}
	}
	
	@Test
	public void search8() {
		// init
		Date dob = StringUtils.convertToDate("1998-11-12");
		search = new Search();
		search.setDob(dob);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(1, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertEquals(dob, insurance.getUser().getDob());
		}
	}
	
	@Test
	public void search9() {
		// init
		Date startDate = StringUtils.convertToDate("2020-03-23");
		search = new Search();
		search.setStartDate(startDate);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(4, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertTrue(startDate.before(insurance.getRegDate()) || startDate.equals(insurance.getRegDate()));
		}
	}
	
	@Test
	public void search10() {
		// init
		Date endDate = StringUtils.convertToDate("2020-06-05");
		search = new Search();
		search.setEndDate(endDate);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(4, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertTrue(endDate.after(insurance.getRegDate()) || endDate.equals(insurance.getRegDate()));
		}
	}
	
	@Test
	public void search11() {
		// init
		Date startDate = StringUtils.convertToDate("2020-03-23");
		Date endDate = StringUtils.convertToDate("2020-06-05");
		search = new Search();
		search.setStartDate(startDate);
		search.setEndDate(endDate);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(4, insurances.size());
		for (Insurance insurance : insurances) {
			Assert.assertTrue(startDate.before(insurance.getRegDate()) || startDate.equals(insurance.getRegDate()));
			Assert.assertTrue(endDate.after(insurance.getRegDate()) || endDate.equals(insurance.getRegDate()));
		}
	}
	
	@Test
	public void search12() {
		// init
		Date startDate = StringUtils.convertToDate("2020-06-05");
		Date endDate = StringUtils.convertToDate("2020-03-23");
		search = new Search();
		search.setStartDate(startDate);
		search.setEndDate(endDate);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(0, insurances.size());
		
	}
	
	@Test
	public void search13() {
		// init
		String name = "ten la   gi";
		search = new Search();
		search.setName(name);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(0, insurances.size());
	}
	
	@Test
	public void search14() {
		// init
		Long identityCard = 12345678956L;
		search = new Search();
		search.setIdentityCard(identityCard);
		// get result by dao function
		insurances = insuranceDao.search(search);
		// test
		Assert.assertNotNull(insurances);
		Assert.assertEquals(0, insurances.size());
		
	}
}
