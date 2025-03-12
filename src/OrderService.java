import java.util.List;

public class OrderService {
    private OrderRepo orderRepo = new OrderRepo();

    public void createOrder(String customerName, String productName, int quantity) {
        orderRepo.createOrder(customerName, productName, quantity);
    }

    //visa alla orders från DB
    public List<String> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    //visa en kunds historik
    public List<String> getOrderHistory(String customerName) {
        return orderRepo.getOrderHistory(customerName);
    }
}
