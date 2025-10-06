package org.example.service;




import org.example.entity.Product;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        if (updatedProduct.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        existing.setDescription(updatedProduct.getDescription());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setLowStockThreshold(updatedProduct.getLowStockThreshold());
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Product increaseStock(Long id, int quantity) {
        Product product = getProductById(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        return repository.save(product);
    }

    public Product decreaseStock(Long id, int quantity) {
        Product product = getProductById(id);
        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available");
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        return repository.save(product);
    }

    public List<Product> getLowStockProducts() {
        return repository.findByStockQuantityLessThan(10);
    }
}
