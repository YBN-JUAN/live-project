package service.impl;

import dao.RecordDAO;
import dao.RecordDAOInterface;
import pojo.Record;
import service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

    private RecordDAOInterface dao = new RecordDAO();

    @Override
    public boolean canRegister(String userId, String phoneNumber, int orderId) {
        return dao.ableOrder(orderId, userId, phoneNumber);
    }

    @Override
    public int Register(String userId, String phoneNumber, int orderId, int maskNum) {
        Record record = new Record();
        record.setUserId(userId);
        record.setTelNum(phoneNumber);
        record.setOrderId(orderId);
        record.setOrderNum(maskNum);
        return dao.add(record).getId();
    }
}
