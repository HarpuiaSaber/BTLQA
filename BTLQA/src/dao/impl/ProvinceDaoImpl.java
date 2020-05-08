package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.ProvinceDao;
import model.Province;

public class ProvinceDaoImpl implements ProvinceDao {

	@Override
	public List<Province> getAll() {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<Province> provinces = new ArrayList<Province>();
		String sql = "SELECT p.id, p.name FROM province AS p";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Province province = new Province();
				province.setId(resultSet.getString("id"));
				province.setName(resultSet.getString("name"));
				provinces.add(province);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return provinces;
	}

}
