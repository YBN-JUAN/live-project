package dao;

import pojo.Record;

import java.util.List;

public interface RecordDAOInterface {
	Record addRecord(Record record);

	boolean setSelectedTrue(int id);

	Record getRecordById(int id);

	Record getByUserID(String userId, int orderId);

	Record getByTelNum(String telNum, int orderId);

	List<Record> list(int orderId);

	boolean ableOrder(int orderId, String userId, String telNum);
}
