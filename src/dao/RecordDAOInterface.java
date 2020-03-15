package dao;

import java.util.List;

import pojo.Record;

public interface RecordDAOInterface {
	Record add(Record record);
	boolean setSelectedTrue(int id);
	Record getById(int id);
	Record getByUserID(String userId, int orderId);
	Record getByTelNum(String telNum, int orderId);
	List<Record> list(int orderId);
	boolean ableOrder(int orderId, String userId, String telNum);
}
