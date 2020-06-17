package test;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.impl.MethodDaoImpl;
import model.Method;

public class TestMethodDao {
	private MethodDaoImpl methodDao = new MethodDaoImpl();
	private Connection connection = methodDao.connection;

	@Test
	public void getAll() {
		List<Method> methods = methodDao.getAll();
		Assert.assertNotNull(methods);
		Assert.assertEquals(7, methods.size());
	}

	@Test
	public void get() {
		Integer id = 5;
		Method method = methodDao.get(id);
		Assert.assertNotNull(method);
		Assert.assertEquals(id, method.getId());
	}

	@Test
	public void insert() {
		Method method = new Method();
		method.setName("new method");
		method.setMonth(100);
		try {
			connection.setAutoCommit(false);
			methodDao.insert(method);
			
			Assert.assertNotNull(method);
			Assert.assertTrue(method.getId() > 27);
			Assert.assertEquals(8, methodDao.getAll().size());
			
			Method method2 = methodDao.get(method.getId());
			Assert.assertEquals(method.getName(), method2.getName());
			Assert.assertEquals(method.getMonth(), method2.getMonth());
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
		Method method = new Method();
		method.setId(5);
		method.setName("new method");
		method.setMonth(100);
		try {
			connection.setAutoCommit(false);
			methodDao.update(method);
			
			Assert.assertEquals(7, methodDao.getAll().size());
			
			Method method2 = methodDao.get(method.getId());
			Assert.assertEquals(method.getName(), method2.getName());
			Assert.assertEquals(method.getMonth(), method2.getMonth());
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
		Integer id = 5;
		try {
			connection.setAutoCommit(false);
			methodDao.delete(id);
			
			Assert.assertEquals(6, methodDao.getAll().size());
			
			Method method2 = methodDao.get(id);
			Assert.assertNull(method2);
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
