package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoConnection;
import dao.DeathModeDao;
import model.DeathMode;

public class DeathModeDaoImpl extends DaoConnection implements DeathModeDao {

	@Override
	public void insert(DeathMode deathMode) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "INSERT INTO death_mode(baseSalary, coefficient, year) VALUES(?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDouble(1, deathMode.getBaseSalary());
			preparedStatement.setDouble(2, deathMode.getCoefficient());
			preparedStatement.setDouble(3, deathMode.getYear());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				deathMode.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);

	}

	@Override
	public void update(DeathMode deathMode) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "UPDATE death_mode SET baseSalary = ?, coefficient = ?, year = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, deathMode.getBaseSalary());
			preparedStatement.setDouble(2, deathMode.getCoefficient());
			preparedStatement.setDouble(3, deathMode.getYear());
			preparedStatement.setInt(4, deathMode.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);

	}

	@Override
	public void delete(int id) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "DELETE FROM death_mode WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
	}

	@Override
	public DeathMode get(int id) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "SELECT * FROM death_mode WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DeathMode mode = new DeathMode();
				mode.setId(id);
				mode.setBaseSalary(resultSet.getDouble("baseSalary"));
				mode.setCoefficient(resultSet.getDouble("coefficient"));
				mode.setYear(resultSet.getDouble("year"));
				return mode;
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return null;
	}

	@Override
	public List<DeathMode> getAll() {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		List<DeathMode> modes = new ArrayList<DeathMode>();
		String sql = "SELECT * FROM death_mode";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DeathMode mode = new DeathMode();
				mode.setId(resultSet.getInt("id"));
				mode.setBaseSalary(resultSet.getDouble("baseSalary"));
				mode.setCoefficient(resultSet.getDouble("coefficient"));
				mode.setYear(resultSet.getDouble("year"));
				modes.add(mode);
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return modes;
	}
}
