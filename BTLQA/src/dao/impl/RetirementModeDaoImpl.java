package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoConnection;
import dao.RetirementModeDao;
import model.RetirementMode;

public class RetirementModeDaoImpl extends DaoConnection implements RetirementModeDao {

	@Override
	public void insert(RetirementMode retirementMode) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "INSERT INTO retirement_mode(year, gender, old, percentMin, percentMax, increase, time) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, retirementMode.getYear());
			preparedStatement.setString(2, retirementMode.getGender());
			preparedStatement.setInt(3, retirementMode.getOld());			
			preparedStatement.setDouble(4, retirementMode.getMinPercent());
			preparedStatement.setDouble(5, retirementMode.getMaxPercent());
			preparedStatement.setDouble(6, retirementMode.getIncrease());
			preparedStatement.setString(7, retirementMode.getTime());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				retirementMode.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
	}

	@Override
	public void update(RetirementMode retirementMode) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "UPDATE retirement_mode SET year = ?, gender = ?, old = ?, percentMin = ?, percentMax = ?, increase = ?, time= ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, retirementMode.getYear());
			preparedStatement.setString(2, retirementMode.getGender());
			preparedStatement.setInt(3, retirementMode.getOld());			
			preparedStatement.setDouble(4, retirementMode.getMinPercent());
			preparedStatement.setDouble(5, retirementMode.getMaxPercent());
			preparedStatement.setDouble(6, retirementMode.getIncrease());
			preparedStatement.setString(7, retirementMode.getTime());
			preparedStatement.setInt(8, retirementMode.getId());
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
		String sql = "DELETE FROM retirement_mode WHERE id = ?";
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
	public RetirementMode get(int id) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		String sql = "SELECT * FROM retirement_mode WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RetirementMode mode = new RetirementMode();
				mode.setId(id);
				mode.setTime(resultSet.getString("time"));
				mode.setYear(resultSet.getInt("year"));
				mode.setGender(resultSet.getString("gender"));
				mode.setMaxPercent(resultSet.getDouble("percentMax"));
				mode.setMinPercent(resultSet.getDouble("percentMin"));
				mode.setOld(resultSet.getInt("old"));
				mode.setIncrease(resultSet.getDouble("increase"));
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
	public List<RetirementMode> getAll() {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		List<RetirementMode> modes = new ArrayList<RetirementMode>();
		String sql = "SELECT * FROM retirement_mode";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RetirementMode mode = new RetirementMode();
				mode.setId(resultSet.getInt("id"));
				mode.setTime(resultSet.getString("time"));
				mode.setYear(resultSet.getInt("year"));
				mode.setGender(resultSet.getString("gender"));
				mode.setMaxPercent(resultSet.getDouble("percentMax"));
				mode.setMinPercent(resultSet.getDouble("percentMin"));
				mode.setOld(resultSet.getInt("old"));
				mode.setIncrease(resultSet.getDouble("increase"));
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
