package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Dao;
import dao.UserDao;
import model.Role;
import model.User;
import model.Village;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(String username) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		String sql = "SELECT u.*, r.name AS roleName FROM user AS u INNER JOIN role AS r ON u.idRole = r.id WHERE u.username like ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setDob(new Date(resultSet.getDate("dob").getTime()));
				user.setGender(resultSet.getString("gender"));
				user.setIdentityCard(resultSet.getLong("identityCard"));				
				user.setUsername(username);
				user.setPassword(resultSet.getString("password"));
				Role role = new Role();
				role.setId(resultSet.getInt("idRole"));
				role.setName(resultSet.getString("roleName"));
				user.setRole(role);
				user.setContactPerson(resultSet.getString("contactPerson"));
				Village village = new Village();
				village.setId(resultSet.getString("idVillage"));
				user.setVillage(village);
				user.setIsActive(resultSet.getBoolean("isActive"));
				pool.freeConnection(connection);
				return user;
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return null;
	}

}
