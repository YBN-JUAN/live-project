package dao;

import pojo.Record;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RecordDAO implements RecordDAOInterface {
	@Override
	public Record add(Record record) {
		return null;
	}

	@Override
	public boolean setSelectedTrue(int id) {
		return false;
	}

	@Override
	public Record getById(int id) {
		return null;
	}

	@Override
	public Record getByUserID(String userId, int orderId) {
		return null;
	}

	@Override
	public Record getByTelNum(String telNum, int orderId) {
		Record record = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from records where "
					+ "telNum = \'" + telNum + "\'"
					+ " and orderid = " + orderId;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
                record = new Record();
				record.setId(rs.getInt("id"));
				record.setOrderId(rs.getInt("orderid"));
				record.setUserId(rs.getString("userid"));
				record.setTelNum(rs.getString("telnum"));
				record.setOrderNum(rs.getInt("ordernum"));
				record.setSelected(rs.getBoolean("selected"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return record;
	}

	@Override
	public List<Record> list(int orderId) {
		return null;
	}

	@Override
	public boolean ableOrder(int orderId, String userId, String telNum) {
		return false;
	}
}
