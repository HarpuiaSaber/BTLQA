package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	private static Dao connectionPool = null;
	private static DataSource dataSource = null;

	private Dao() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/bhxh");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Dao getInstance() {
		if (connectionPool == null) {
			connectionPool = new Dao();
		}
		return connectionPool;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}

	public void freeConnection(Connection c) {
		try {			
			c.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
