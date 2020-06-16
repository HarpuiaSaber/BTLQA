package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnection {
	public static Connection connection;

	public DaoConnection() {
		getInstance();
	}

	public void getInstance() {
		if (connection == null) {
			String url = "jdbc:mysql://localhost:3306/bhxh?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
			String user = "root";
			String password = "250498";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
