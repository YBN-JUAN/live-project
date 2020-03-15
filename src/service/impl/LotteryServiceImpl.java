package service.impl;

import dao.OrderDAOInterface;
import service.LotteryService;

public class LotteryServiceImpl  implements LotteryService {
    private OrderDAOInterface orderDAOInterface =null;
    @Override
    public void endOrder(int orderId) {
        orderDAOInterface.closeOrder(orderId);
    }
}
