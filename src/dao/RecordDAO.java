package dao;

import pojo.Record;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO implements RecordDAOInterface {
	@Override
	public Record add(Record record) {

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "insert into records(orderid, userid, telnum, ordernum) values ("
				+ record.getOrderId() + ","
				+ "\'" + record.getUserId() + "\',"
				+ "\'" + record.getTelNum() + "\',"
				+ record.getOrderNum() + ");";
			s.executeUpdate(sql);
			sql = "select * from records order by id desc";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				record.setId(rs.getInt("id"));
				record.setSelected(rs.getBoolean("selected"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return record;
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
		List<Record> list = new ArrayList<Record>();

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from records where orderId = " + orderId;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setOrderId(rs.getInt("orderid"));
				record.setUserId(rs.getString("userid"));
				record.setTelNum(rs.getString("telnum"));
				record.setOrderNum(rs.getInt("ordernum"));
				record.setSelected(rs.getBoolean("selected"));

				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public boolean ableOrder(int orderId, String userId, String telNum) {
		return false;
	}
}
