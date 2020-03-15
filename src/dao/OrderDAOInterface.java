package dao;

import pojo.Order;

public interface OrderDAOInterface {
	
	Order add(int maskNum);
	Order get(int id);
	int getMaskNum(int id);
	boolean isOpening(int id);
	boolean closeOrder(int id);
}
