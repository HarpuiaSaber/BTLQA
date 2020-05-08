package service;

import model.User;

public interface UserService {
	public User auth(String username, String password);
}
