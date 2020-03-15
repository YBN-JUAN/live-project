package service.impl;

import dao.OrderDAO;
import dao.OrderDAOInterface;
import dao.RecordDAO;
import dao.RecordDAOInterface;
import pojo.Order;
import pojo.Record;
import service.LotteryService;

import java.util.Collections;
import java.util.List;

public class LotteryServiceImpl implements LotteryService {
    private OrderDAOInterface orderDao = new OrderDAO();
    private RecordDAOInterface recordDao = new RecordDAO();

    @Override
    public void endOrder(int orderId) {
        Order order = orderDao.get(orderId);
        int maskNum = order.getMaskNum();

        List<Record> records = recordDao.list(orderId);
        Collections.shuffle(records);
        for (Record record : records) {
            if (maskNum >= record.getOrderNum()) {
                recordDao.setSelectedTrue(record.getId());
                maskNum -= record.getOrderNum();
            } else {
                break;
            }
        }
        orderDao.closeOrder(orderId);
    }

    @Override
    public int startOder(int maskNum) {
        Order order = orderDao.add(maskNum);
        return order.getId();
    }
}
