package dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoConnection;
import dao.InsuranceDao;
import model.District;
import model.Insurance;
import model.Method;
import model.Paging;
import model.Province;
import model.Search;
import model.User;
import model.Village;

public class InsuranceDaoImpl extends DaoConnection implements InsuranceDao {

	@Override
	public List<Insurance> search(Search search) {
		List<Insurance> insurances = new ArrayList<Insurance>();
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT i.*, m.name AS mName, u.name AS uName, u.identityCard, u.gender, u.dob, u.idVillage, v.name AS vName, v.idDistrict, d.name AS dName, d.idProvince, p.name AS pName");
		sql.append(" FROM insurance AS i");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" i.regDate >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" i.regDate < ? AND");
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
		sql.append(" true ORDER BY i.regDate, i.id");
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
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("id"));
				insurance.setRegDate(new Date(resultSet.getDate("regDate").getTime()));
				insurance.setStatus(resultSet.getInt("status"));
				Method method = new Method();
				method.setName(resultSet.getString("mName"));
				insurance.setMethod(method);
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setGender(resultSet.getString("gender"));
				user.setDob(new Date(resultSet.getDate("dob").getTime()));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				Province province = new Province();
				province.setId(resultSet.getString("idProvince"));
				province.setName(resultSet.getString("pName"));
				District district = new District();
				district.setId(resultSet.getString("idDistrict"));
				district.setName(resultSet.getString("dName"));
				district.setProvince(province);
				Village village = new Village();
				village.setId(resultSet.getString("idVillage"));
				village.setName(resultSet.getString("vName"));
				village.setDistrict(district);
				user.setVillage(village);
				insurance.setUser(user);
				insurances.add(insurance);
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return insurances;
	}

	@Override
	public List<Insurance> searchWithPaging(Search search, Paging paging) {
		List<Insurance> insurances = new ArrayList<Insurance>();
//		Dao pool = Dao.getInstance();
//		Connection connection = pool.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY i.regDate, i.id) AS row_num,");
		sql.append(
				" i.*, m.name AS mName, u.name AS uName, u.identityCard, u.gender, u.dob, u.idVillage, v.name AS vName, v.idDistrict, d.name AS dName, d.idProvince, p.name AS pName");
		sql.append(" FROM insurance AS i");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" i.regDate >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" i.regDate < ? AND");
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
				Insurance insurance = new Insurance();
				insurance.setId(resultSet.getLong("id"));
				insurance.setRegDate(new Date(resultSet.getDate("regDate").getTime()));
				insurance.setStatus(resultSet.getInt("status"));
				Method method = new Method();
				method.setName(resultSet.getString("mName"));
				insurance.setMethod(method);
				User user = new User();
				user.setName(resultSet.getString("uName"));
				user.setGender(resultSet.getString("gender"));
				user.setDob(new Date(resultSet.getDate("dob").getTime()));
				user.setIdentityCard(resultSet.getLong("identityCard"));
				Province province = new Province();
				province.setId(resultSet.getString("idProvince"));
				province.setName(resultSet.getString("pName"));
				District district = new District();
				district.setId(resultSet.getString("idDistrict"));
				district.setName(resultSet.getString("dName"));
				district.setProvince(province);
				Village village = new Village();
				village.setId(resultSet.getString("idVillage"));
				village.setName(resultSet.getString("vName"));
				village.setDistrict(district);
				user.setVillage(village);
				insurance.setUser(user);
				insurances.add(insurance);
			}
		} catch (SQLException e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return insurances;
	}

	@Override
	public int getTotalRecord(Search search) {
		//Dao pool = Dao.getInstance();
		//Connection connection = pool.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(row_num) AS totalRecord FROM (SELECT ROW_NUMBER() OVER(ORDER BY i.regDate, i.id) AS row_num");
		sql.append(" FROM insurance AS i");
		sql.append(" INNER JOIN method AS m ON m.id = i.idMethod");
		sql.append(" INNER JOIN user AS u ON u.id = i.idUser");
		sql.append(" INNER JOIN village AS v ON v.id = u.idVillage");
		sql.append(" INNER JOIN district AS d ON d.id = v.idDistrict");
		sql.append(" INNER JOIN province AS p ON p.id = d.idProvince WHERE");
		if (search.getStartDate() != null) {
			sql.append(" i.regDate >= ? AND");
		}
		if (search.getEndDate() != null) {
			sql.append(" i.regDate < ? AND");
		}
		if (search.getIdVillage() != null) {
			sql.append(" v.id = ? AND");
		} else if (search.getIdDistrict() != null) {
			sql.append(" d.id = ? AND");
		} else if (search.getIdProvince() != null) {
			sql.append(" p.id = ? AND");
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
				//pool.freeConnection(connection);
				return total;
			}
		} catch (Exception e) {
			//pool.freeConnection(connection);
			e.printStackTrace();
		}
		//pool.freeConnection(connection);
		return 0;
	}

}
