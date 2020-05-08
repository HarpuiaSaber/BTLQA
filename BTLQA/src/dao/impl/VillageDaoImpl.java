package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.VillageDao;
import model.District;
import model.Village;

public class VillageDaoImpl implements VillageDao{

	@Override
	public List<Village> getByDistrictId(String id) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<Village> villages = new ArrayList<Village>();
		String sql = "SELECT v.id, v.name FROM village AS v where v.idDistrict like ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Village village = new Village();
				village.setId(resultSet.getString("id"));
				village.setName(resultSet.getString("name"));
				District district = new District();
				district.setId(id);
				village.setDistrict(district);
				villages.add(village);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return villages;
	}
	

}
