package dao;

import java.sql.*;

import com.mysql.cj.exceptions.RSAException;

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
	
}
