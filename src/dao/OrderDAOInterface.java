package dao;

import pojo.Order;

public interface OrderDAOInterface {
	
	boolean add(int maskNum);
	Order get(int id);
	int getMaskNum(int id);
	boolean isOpening(int id);
	boolean closeOrder(int id);
}
