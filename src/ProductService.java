import java.util.List;

public class ProductService {
    private ProductRepo productRepo = new ProductRepo();

    public List<String> getAllProducts() {
        return productRepo.getAllProducts();
    }

    public List<String> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }

    public void addProduct(String name, double price, int stockQuantity) {
        productRepo.addProduct(name, price, stockQuantity);
    }

    public boolean isInStock(String productName) {
        return productRepo.isInStock(productName);
    }

    public void updateStock(String productName, int newStock) {
        productRepo.updateStock(productName, newStock);
    }
}
