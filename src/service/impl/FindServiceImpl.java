package service.impl;

import dao.RecordDAO;
import dao.RecordDAOInterface;
import pojo.Record;
import service.FindService;

public class FindServiceImpl implements FindService {
    private RecordDAOInterface recordDAO=new RecordDAO();
    @Override
    public Record getRecord(int id) {
        return recordDAO.getRecordById(id);
    }
}
