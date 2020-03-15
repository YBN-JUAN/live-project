package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pojo.Record;
import util.DBUtil;

public class RecordDAO implements RecordDAOInterface{

	@Override
	public Record add(Record record) {
		// TODO Auto-generated method stub
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "insert into records(orderid,username,userid,telnum,ordernum) values (" 
            			+ record.getOrderId() + ","
            			+ "\'" + record.getUserName() + "\',"
            			+ "\'" + record.getUserId() + "\',"
            			+ "\'" + record.getTelNum() + "\',"
            			+ record.getOrderNum() + ");";
            s.executeUpdate(sql);
            sql = "select * from records order by id desc";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) {
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
		// TODO Auto-generated method stub
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "update records set selected = true where id = " + id; 
            s.executeUpdate(sql);
        } catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
		
		return true;
	}

	@Override
	public Record getById(int id) {
		// TODO Auto-generated method stub
		Record record = null;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from records where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	record = new Record();
                record.setId(rs.getInt("id"));
                record.setUserName(rs.getString("username"));
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
	public Record getByUserId(String userId, int orderId) {
		// TODO Auto-generated method stub
		Record record = null;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from records where "
            			+ "userid = \'" + userId + "\'"
            			+ " and orderid = " + orderId; 
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	record = new Record();
                record.setId(rs.getInt("id"));
                record.setUserName("username");
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
	public Record getByTelNum(String telNum, int orderId) {
		// TODO Auto-generated method stub
		Record record = null;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from records where "
            			+ "telNum = \'" + telNum + "\'"
            			+ " and orderid = " + orderId; 
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	record = new Record();
                record.setId(rs.getInt("id"));
                record.setUserName("username");
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
		// TODO Auto-generated method stub
		List<Record> list = new ArrayList<Record>();
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from records where orderId = " + orderId;
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()) {
            	Record record = new Record();
            	record.setId(rs.getInt("id"));
            	record.setUserName(rs.getString("username"));
            	record.setOrderId(rs.getInt("orderid"));
            	record.setUserId(rs.getString("userid"));
            	record.setTelNum(rs.getString("telnum"));
            	record.setOrderNum(rs.getInt("ordernum"));
            	record.setSelected(rs.getBoolean("selected"));
            	
            	list.add(record);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		
		return list;
		
	}

	@Override
	public boolean ableOrder(int orderId, String userId, String telNum) {
		// TODO Auto-generated method stub
		boolean isExisted = (getByUserId(userId, orderId) != null || getByTelNum(telNum, orderId) != null);
		
		if(isExisted) return false;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select orderid from records where selected = true" 
            			+ " and (userId = \'" + userId + "\'"
            			+ " or telNum = \'" + telNum + "\')"
            			+ " order by orderid desc";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	int maxId = rs.getInt(1);
            	if(orderId - maxId >= 3)
            		return true;
            	else
            		return false;
            }
            else return true;
            	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		
		return false;
	}

}
