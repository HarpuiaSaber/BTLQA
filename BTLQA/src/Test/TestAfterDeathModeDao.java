package Test;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.impl.AfterDeathModeDaoImpl;
import dao.impl.DeathModeDaoImpl;
import model.AfterDeathMode;
import model.DeathMode;

public class TestAfterDeathModeDao {
	private AfterDeathModeDaoImpl afterDeathModeDao = new AfterDeathModeDaoImpl();
	private Connection connection = afterDeathModeDao.connection;
	@Test
	public void getAll() {
		List<AfterDeathMode> list = afterDeathModeDao.getAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());
	}

	@Test
	public void get() {
		Integer id = 1;
		AfterDeathMode afterDeathMode = afterDeathModeDao.get(id);
		Assert.assertNotNull(afterDeathMode);
		Assert.assertEquals(id, afterDeathMode.getId());
	}
//	private Integer id;
//	private String status;
//	private Double month;
//	private String time;
//	private Double reduction;
	
	@Test
	public void insert() {
		AfterDeathMode afterDeathMode = new AfterDeathMode();
		afterDeathMode.setStatus("Đang đóng");
		afterDeathMode.setMonth(2.0);
		afterDeathMode.setReduction(0.0);
		afterDeathMode.setTime("Trước 2015");
		
		try {
			connection.setAutoCommit(false);
			afterDeathModeDao.insert(afterDeathMode);
			
			Assert.assertNotNull(afterDeathMode);
			Assert.assertTrue(afterDeathMode.getId() > 5);
			Assert.assertEquals(5, afterDeathModeDao.getAll().size());
			
			AfterDeathMode afterDeathMode2 = afterDeathModeDao.get(afterDeathMode.getId());
			Assert.assertEquals(afterDeathMode.getStatus(), afterDeathMode2.getStatus());
			Assert.assertEquals(afterDeathMode.getMonth(), afterDeathMode2.getMonth());
			Assert.assertEquals(afterDeathMode.getReduction(), afterDeathMode2.getReduction());
			Assert.assertEquals(afterDeathMode.getTime(), afterDeathMode2.getTime());
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
		AfterDeathMode afterDeathMode = new AfterDeathMode();
		afterDeathMode.setId(1);
		afterDeathMode.setMonth(3.0);
		afterDeathMode.setTime("Trước 2016");
		
		try {
			connection.setAutoCommit(false);
			afterDeathModeDao.update(afterDeathMode);
			
			Assert.assertEquals(4, afterDeathModeDao.getAll().size());
			
			AfterDeathMode afterDeathMode2 = afterDeathModeDao.get(afterDeathMode.getId());
		//	Assert.assertEquals(afterDeathMode.getStatus(), afterDeathMode2.getStatus());
			Assert.assertEquals(afterDeathMode.getMonth(), afterDeathMode2.getMonth());
		//	Assert.assertEquals(afterDeathMode.getReduction(), afterDeathMode2.getReduction());
			Assert.assertEquals(afterDeathMode.getTime(), afterDeathMode2.getTime());
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
			afterDeathModeDao.delete(id);
			
			Assert.assertEquals(3, afterDeathModeDao.getAll().size());
			
			AfterDeathMode afterDeathMode2 = afterDeathModeDao.get(id);
			Assert.assertNull(afterDeathMode2);
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
