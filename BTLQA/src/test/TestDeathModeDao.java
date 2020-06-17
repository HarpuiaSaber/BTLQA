package test;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.impl.DeathModeDaoImpl;
import model.DeathMode;

public class TestDeathModeDao {
	private DeathModeDaoImpl deathModeDao = new DeathModeDaoImpl();
	private Connection connection = deathModeDao.connection;

	@Test
	public void getAll() {
		List<DeathMode> list = deathModeDao.getAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void get() {
		Integer id = 1;
		DeathMode deathMode = deathModeDao.get(id);
		Assert.assertNotNull(deathMode);
		Assert.assertEquals(id, deathMode.getId());
	}

	@Test
	public void insert() {
		DeathMode deathMode = new DeathMode();
		deathMode.setBaseSalary(10000000D);
		deathMode.setCoefficient(1.5);
		deathMode.setYear(10.5);
		try {
			connection.setAutoCommit(false);
			deathModeDao.insert(deathMode);
			
			Assert.assertNotNull(deathMode);
			Assert.assertTrue(deathMode.getId() > 11);
			Assert.assertEquals(3, deathModeDao.getAll().size());
			
			DeathMode deathMode2 = deathModeDao.get(deathMode.getId());
			Assert.assertEquals(deathMode.getBaseSalary(), deathMode2.getBaseSalary());
			Assert.assertEquals(deathMode.getCoefficient(), deathMode2.getCoefficient());
			Assert.assertEquals(deathMode.getYear(), deathMode2.getYear());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void update() {
		DeathMode deathMode = new DeathMode();
		deathMode.setId(1);
		deathMode.setBaseSalary(10000000D);
		deathMode.setCoefficient(1.5);
		deathMode.setYear(10.5);
		try {
			connection.setAutoCommit(false);
			deathModeDao.update(deathMode);
			
			Assert.assertEquals(2, deathModeDao.getAll().size());
			
			DeathMode deathMode2 = deathModeDao.get(deathMode.getId());
			Assert.assertEquals(deathMode.getBaseSalary(), deathMode2.getBaseSalary());
			Assert.assertEquals(deathMode.getCoefficient(), deathMode2.getCoefficient());
			Assert.assertEquals(deathMode.getYear(), deathMode2.getYear());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void delete() {
		Integer id = 1;
		try {
			connection.setAutoCommit(false);
			deathModeDao.delete(id);
			
			Assert.assertEquals(1, deathModeDao.getAll().size());
			
			DeathMode deathMode2 = deathModeDao.get(id);
			Assert.assertNull(deathMode2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
