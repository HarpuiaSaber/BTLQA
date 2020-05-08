package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.DistrictDao;
import model.District;
import model.Province;

public class DistrictDaoImpl implements DistrictDao{

	@Override
	public List<District> getByProvinceId(String id) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<District> districts = new ArrayList<District>();
		String sql = "SELECT d.id, d.name FROM district AS d where d.idProvince like ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				District district = new District();
				district.setId(resultSet.getString("id"));
				district.setName(resultSet.getString("name"));
				Province province = new Province();
				province.setId(id);
				district.setProvince(province);
				districts.add(district);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return districts;
	}

}
