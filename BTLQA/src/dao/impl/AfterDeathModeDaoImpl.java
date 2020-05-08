package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.AfterDeathModeDao;
import dao.Dao;
import model.AfterDeathMode;

public class AfterDeathModeDaoImpl implements AfterDeathModeDao {

	@Override
	public void insert(AfterDeathMode afterDeathMode) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "INSERT INTO after_death_mode(time, month, status, reduction) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, afterDeathMode.getTime());
			preparedStatement.setDouble(2, afterDeathMode.getMonth());
			preparedStatement.setString(3, afterDeathMode.getStatus());
			preparedStatement.setDouble(4, afterDeathMode.getReduction());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				afterDeathMode.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);

	}

	@Override
	public void update(AfterDeathMode afterDeathMode) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "UPDATE after_death_mode SET time = ?, month = ?, status = ?, reduction = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, afterDeathMode.getTime());
			preparedStatement.setDouble(2, afterDeathMode.getMonth());
			preparedStatement.setString(3, afterDeathMode.getStatus());
			preparedStatement.setDouble(4, afterDeathMode.getReduction());
			preparedStatement.setInt(5, afterDeathMode.getId());
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
		String sql = "DELETE FROM after_death_mode WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
	}

	@Override
	public AfterDeathMode get(int id) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "SELECT * FROM after_death_mode WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AfterDeathMode mode = new AfterDeathMode();
				mode.setId(id);
				mode.setTime(resultSet.getString("time"));
				mode.setMonth(resultSet.getDouble("month"));
				mode.setStatus(resultSet.getString("status"));
				mode.setReduction(resultSet.getDouble("reduction"));
				return mode;
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return null;
	}

	@Override
	public List<AfterDeathMode> getAll() {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<AfterDeathMode> modes = new ArrayList<AfterDeathMode>();
		String sql = "SELECT * FROM after_death_mode";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AfterDeathMode mode = new AfterDeathMode();
				mode.setId(resultSet.getInt("id"));
				mode.setTime(resultSet.getString("time"));
				mode.setMonth(resultSet.getDouble("month"));
				mode.setStatus(resultSet.getString("status"));
				mode.setReduction(resultSet.getDouble("reduction"));
				modes.add(mode);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return modes;
	}

}
