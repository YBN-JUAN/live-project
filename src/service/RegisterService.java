package service;

public interface RegisterService {
    boolean canRegister(String userId, String phoneNumber, int orderId);

    int Register(String userId, String phoneNumber, int orderId, int maskNum);
}
