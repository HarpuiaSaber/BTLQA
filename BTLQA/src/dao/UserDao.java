package dao;

import model.User;

public interface UserDao {
	public User get(String username);
}
