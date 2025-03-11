import java.util.List;

public class OrderService {
    private OrderRepo orderRepo = new OrderRepo();

    public void createOrder(String customerName, String productName, int quantity) {
        orderRepo.createOrder(customerName, productName, quantity);
    }

    //Show all orders from the DB
    public List<String> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    //Show a single customer's order history
    public List<String> getOrderHistory(String customerName) {
        return orderRepo.getOrderHistory(customerName);
    }
}
