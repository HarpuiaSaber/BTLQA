package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.MethodDao;
import model.Method;

public class MethodDaoImpl implements MethodDao {

	@Override
	public void insert(Method method) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "INSERT INTO method(month, name) VALUES(?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, method.getMonth());
			preparedStatement.setString(2, method.getName());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				method.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
	}

	@Override
	public void update(Method method) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "UPDATE method SET month = ?, name = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, method.getMonth());
			preparedStatement.setString(2, method.getName());
			preparedStatement.setInt(3, method.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
	}

	@Override
	public void delete(int id) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "DELETE FROM method WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
	}

	@Override
	public Method get(int id) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "SELECT * FROM method WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Method method = new Method();
				method.setId(id);
				method.setName(resultSet.getString("name"));
				method.setMonth(resultSet.getInt("month"));
				return method;
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return null;
	}

	@Override
	public List<Method> getAll() {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<Method> methods = new ArrayList<Method>();
		String sql = "SELECT * FROM method";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Method method = new Method();
				method.setId(resultSet.getInt("id"));
				method.setName(resultSet.getString("name"));
				method.setMonth(resultSet.getInt("month"));
				methods.add(method);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return methods;
	}

}
