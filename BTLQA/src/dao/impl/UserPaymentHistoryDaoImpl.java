package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.UserPaymentHistoryDao;
import model.Insurance;
import model.Method;
import model.Paging;
import model.Search;
import model.User;
import model.UserPaymentHistory;

public class UserPaymentHistoryDaoImpl implements UserPaymentHistoryDao {

	@Override
	public List<UserPaymentHistory> search(Search search) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<UserPaymentHistory> histories = new ArrayList<UserPaymentHistory>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT pa.*, m.name AS mName, u.name AS uName, u.identityCard");
		sql.append(" FROM user_payment_history AS pa");
		sql.append(" INNER JOIN insurance AS i ON i.id = pa.idInsurance");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" pa.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" pa.time < ? AND");
		}
		if (search.getIdVillage() != null) {
			sql.append(" v.id like ? AND");
		} else if (search.getIdDistrict() != null) {
			sql.append(" d.id like ? AND");
		} else if (search.getIdProvince() != null) {
			sql.append(" p.id like ? AND");
		}
		if (search.getIdentityCard() != null) {
			sql.append(" u.identityCard = ? AND");
		}
		if (search.getDob() != null) {
			sql.append(" u.dob = ? AND");
		}
		if (search.getName() != null) {
			sql.append(" u.name = ? AND");
		}
		sql.append(" true ORDER BY pa.time, pa.id");
		int index = 1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			if (search.getStartDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getStartDate().getTime()));
			}
			if (search.getEndDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getEndDate().getTime()));
			}
			if (search.getIdVillage() != null) {
				preparedStatement.setString(index++, search.getIdVillage());
			} else if (search.getIdDistrict() != null) {
				preparedStatement.setString(index++, search.getIdDistrict());
			} else if (search.getIdProvince() != null) {
				preparedStatement.setString(index++, search.getIdProvince());
			}
			if (search.getIdentityCard() != null) {
				preparedStatement.setLong(index++, search.getIdentityCard());
			}
			if (search.getDob() != null) {
				preparedStatement.setDate(index++, new Date(search.getDob().getTime()));
			}
			if (search.getName() != null) {
				preparedStatement.setString(index, search.getName());
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserPaymentHistory history = new UserPaymentHistory();
				history.setId(resultSet.getLong("id"));
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("idInsurance"));
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				insurance.setUser(user);
				Method method = new Method();
				method.setName(resultSet.getString("mName"));
				insurance.setMethod(method);
				history.setInsurance(insurance);
				history.setTime(new Date(resultSet.getDate("time").getTime()));
				history.setTransactionId(resultSet.getLong("idTransaction"));
				history.setBankName(resultSet.getString("bank"));
				history.setCost(resultSet.getDouble("cost"));
				histories.add(history);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return histories;
	}

	@Override
	public List<UserPaymentHistory> searchWithPaging(Search search, Paging paging) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		List<UserPaymentHistory> histories = new ArrayList<UserPaymentHistory>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY pa.time, pa.id) AS row_num,");
		sql.append(" pa.*, m.name AS mName, u.name AS uName, u.identityCard");
		sql.append(" FROM user_payment_history AS pa");
		sql.append(" INNER JOIN insurance AS i ON i.id = pa.idInsurance");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" pa.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" pa.time < ? AND");
		}
		if (search.getIdVillage() != null) {
			sql.append(" v.id like ? AND");
		} else if (search.getIdDistrict() != null) {
			sql.append(" d.id like ? AND");
		} else if (search.getIdProvince() != null) {
			sql.append(" p.id like ? AND");
		}
		if (search.getIdentityCard() != null) {
			sql.append(" u.identityCard = ? AND");
		}
		if (search.getDob() != null) {
			sql.append(" u.dob = ? AND");
		}
		if (search.getName() != null) {
			sql.append(" u.name = ? AND");
		}
		sql.append(" true");
		sql.append(") AS t WHERE t.row_num > ? LIMIT ?");
		int index = 1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			if (search.getStartDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getStartDate().getTime()));
			}
			if (search.getEndDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getEndDate().getTime()));
			}
			if (search.getIdVillage() != null) {
				preparedStatement.setString(index++, search.getIdVillage());
			} else if (search.getIdDistrict() != null) {
				preparedStatement.setString(index++, search.getIdDistrict());
			} else if (search.getIdProvince() != null) {
				preparedStatement.setString(index++, search.getIdProvince());
			}
			if (search.getIdentityCard() != null) {
				preparedStatement.setLong(index++, search.getIdentityCard());
			}
			if (search.getDob() != null) {
				preparedStatement.setDate(index++, new Date(search.getDob().getTime()));
			}
			if (search.getName() != null) {
				preparedStatement.setString(index++, search.getName());
			}
			preparedStatement.setInt(index++, paging.getBeginIndex());
			preparedStatement.setInt(index, paging.getPageSize());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserPaymentHistory history = new UserPaymentHistory();
				history.setId(resultSet.getLong("id"));
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("idInsurance"));
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				insurance.setUser(user);
				Method method = new Method();
				method.setName(resultSet.getString("mName"));
				insurance.setMethod(method);
				history.setInsurance(insurance);
				history.setTime(new Date(resultSet.getDate("time").getTime()));
				history.setTransactionId(resultSet.getLong("idTransaction"));
				history.setBankName(resultSet.getString("bank"));
				history.setCost(resultSet.getDouble("cost"));
				histories.add(history);
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return histories;
	}

	@Override
	public int getTotalRecord(Search search) {
		Dao pool = Dao.getInstance();
		Connection connection = pool.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(row_num) AS totalRecord FROM (SELECT ROW_NUMBER() OVER(ORDER BY pa.time, pa.id) AS row_num");
		sql.append(" FROM user_payment_history AS pa");
		sql.append(" INNER JOIN insurance AS i ON i.id = pa.idInsurance");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" pa.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" pa.time < ? AND");
		}
		if (search.getIdVillage() != null) {
			sql.append(" v.id like ? AND");
		} else if (search.getIdDistrict() != null) {
			sql.append(" d.id like ? AND");
		} else if (search.getIdProvince() != null) {
			sql.append(" p.id like ? AND");
		}
		if (search.getIdentityCard() != null) {
			sql.append(" u.identityCard = ? AND");
		}
		if (search.getDob() != null) {
			sql.append(" u.dob = ? AND");
		}
		if (search.getName() != null) {
			sql.append(" u.name = ? AND");
		}
		sql.append(" true");
		sql.append(") AS t");
		int index = 1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			if (search.getStartDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getStartDate().getTime()));
			}
			if (search.getEndDate() != null) {
				preparedStatement.setDate(index++, new Date(search.getEndDate().getTime()));
			}
			if (search.getIdVillage() != null) {
				preparedStatement.setString(index++, search.getIdVillage());
			} else if (search.getIdDistrict() != null) {
				preparedStatement.setString(index++, search.getIdDistrict());
			} else if (search.getIdProvince() != null) {
				preparedStatement.setString(index++, search.getIdProvince());
			}
			if (search.getIdentityCard() != null) {
				preparedStatement.setLong(index++, search.getIdentityCard());
			}
			if (search.getDob() != null) {
				preparedStatement.setDate(index++, new Date(search.getDob().getTime()));
			}
			if (search.getName() != null) {
				preparedStatement.setString(index, search.getName());
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int total = resultSet.getInt("totalRecord");
				pool.freeConnection(connection);
				return total;
			}
		} catch (SQLException e) {
			pool.freeConnection(connection);
			e.printStackTrace();
		}
		pool.freeConnection(connection);
		return 0;
	}

}
