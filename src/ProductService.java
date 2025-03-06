// ProductService.java
import java.util.List;

public class ProductService {
    private ProductRepo productRepo = new ProductRepo();

    public List<String> getAllProducts() {
        return productRepo.getAllProducts();
    }

    public List<String> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}