package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public User auth(String username, String password) {
		User user = userDao.get(username);
		if (user != null && user.getIsActive() == true && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
