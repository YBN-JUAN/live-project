package dao;

import java.sql.*;


import pojo.Order;
import util.DBUtil;

public class OrderDAO implements OrderDAOInterface{

	@Override
	public Order add(int maskNum) {
		// TODO Auto-generated method stub
		Order order = new Order();
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "insert into orders(masknum) values (" + maskNum + ");"; 
            s.executeUpdate(sql);
            sql = "select * from orders order by id desc";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) {
	            order.setId(rs.getInt("id"));
	            order.setOpening(rs.getBoolean("opening"));
	            order.setMaskNum(rs.getInt("masknum"));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        }
		
		return order;
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		Order order = null;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from orders where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            order = new Order();
            if (rs.next()) {
                order.setId(id);
                order.setOpening(rs.getBoolean("opening"));
                order.setMaskNum(rs.getInt("masknum"));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		
		return order;
	}
	
	@Override
	public int getMaskNum(int id) {
		// TODO Auto-generated method stub
		int maskNum = 0;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select masknum from orders where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                maskNum = rs.getInt(1);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	maskNum = -1;
        }
		
        return maskNum;
		
	}

	@Override
	public boolean isOpening(int id) {
		// TODO Auto-generated method stub
		boolean opening = true;
		
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select opening from orders where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                opening = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return opening;
	}

	@Override
	public boolean closeOrder(int id) {
		// TODO Auto-generated method stub
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "update orders set opening = false where id = " + id;
            s.executeUpdate(sql);
        } catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
		
		return true;
	}
	
}
