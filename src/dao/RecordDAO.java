package dao;

import pojo.Record;

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
		return null;
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
