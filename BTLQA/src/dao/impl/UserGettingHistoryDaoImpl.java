package dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoConnection;
import dao.UserGettingHistoryDao;
import model.District;
import model.Insurance;
import model.Paging;
import model.Province;
import model.Search;
import model.User;
import model.UserGettingHistory;
import model.Village;

public class UserGettingHistoryDaoImpl extends DaoConnection implements UserGettingHistoryDao {

	@Override
	public List<UserGettingHistory> search(Search search) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		List<UserGettingHistory> histories = new ArrayList<UserGettingHistory>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT gh.*, u.name AS uName, u.identityCard, u.dob, u.idVillage, v.idDistrict, d.idProvince");
		sql.append(" FROM user_getting_history AS gh");
		sql.append(" INNER JOIN insurance AS i ON i.id = gh.idInsurance");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" gh.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" gh.time < ? AND");
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
		if (search.getMode() != null) {
			sql.append(" gh.mode = ? AND");
		}
		sql.append(" true ORDER BY gh.time, gh.id");
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
			if (search.getMode() != null) {
				preparedStatement.setInt(index++, search.getMode());
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserGettingHistory history = new UserGettingHistory();
				history.setId(resultSet.getLong("id"));
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("idInsurance"));
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				user.setDob(new Date(resultSet.getDate("dob").getTime()));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				Province province = new Province();
				province.setId(resultSet.getString("idProvince"));
				District district = new District();
				district.setId(resultSet.getString("idDistrict"));
				district.setProvince(province);
				Village village = new Village();
				village.setId(resultSet.getString("idVillage"));
				village.setDistrict(district);
				user.setVillage(village);
				insurance.setUser(user);
				history.setInsurance(insurance);
				history.setTime(new Date(resultSet.getDate("time").getTime()));
				history.setTransactionId(resultSet.getLong("idTransaction"));
				history.setBankName(resultSet.getString("bank"));
				history.setCost(resultSet.getDouble("cost"));
				history.setMode(resultSet.getInt("mode"));
				history.setModeId(resultSet.getInt("idMode"));
				histories.add(history);
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return histories;
	}

	@Override
	public List<UserGettingHistory> searchWithPaging(Search search, Paging paging) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		List<UserGettingHistory> histories = new ArrayList<UserGettingHistory>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY gh.time, gh.id) AS row_num,");
		sql.append(" gh.*, u.name AS uName, u.identityCard, u.dob, u.idVillage, v.idDistrict, d.idProvince");
		sql.append(" FROM user_getting_history AS gh");
		sql.append(" INNER JOIN insurance AS i ON i.id = gh.idInsurance");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" gh.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" gh.time < ? AND");
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
		if (search.getMode() != null) {
			sql.append(" gh.mode = ? AND");
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
			if (search.getMode() != null) {
				preparedStatement.setInt(index++, search.getMode());
			}
			preparedStatement.setInt(index++, paging.getBeginIndex());
			preparedStatement.setInt(index, paging.getPageSize());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserGettingHistory history = new UserGettingHistory();
				history.setId(resultSet.getLong("id"));
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("idInsurance"));
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				user.setDob(new Date(resultSet.getDate("dob").getTime()));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				Province province = new Province();
				province.setId(resultSet.getString("idProvince"));
				District district = new District();
				district.setId(resultSet.getString("idDistrict"));
				district.setProvince(province);
				Village village = new Village();
				village.setId(resultSet.getString("idVillage"));
				village.setDistrict(district);
				user.setVillage(village);
				insurance.setUser(user);
				history.setInsurance(insurance);
				history.setTime(new Date(resultSet.getDate("time").getTime()));
				history.setTransactionId(resultSet.getLong("idTransaction"));
				history.setBankName(resultSet.getString("bank"));
				history.setCost(resultSet.getDouble("cost"));
				history.setMode(resultSet.getInt("mode"));
				history.setModeId(resultSet.getInt("idMode"));
				histories.add(history);
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return histories;
	}

	@Override
	public int getTotalRecord(Search search) {
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(row_num) AS totalRecord FROM (SELECT ROW_NUMBER() OVER(ORDER BY gh.time, gh.id) AS row_num");
		sql.append(" FROM user_getting_history AS gh");
		sql.append(" INNER JOIN insurance AS i ON i.id = gh.idInsurance");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" gh.time >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" gh.time < ? AND");
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
		if (search.getMode() != null) {
			sql.append(" gh.mode = ? AND");
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
			if (search.getMode() != null) {
				preparedStatement.setInt(index++, search.getMode());
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int total = resultSet.getInt("totalRecord");
				//pool.freeConnection(connection);
				return total;
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return 0;
	}

}
