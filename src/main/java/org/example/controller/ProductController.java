package org.example.controller;


import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = service.createProduct(product);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product updated = service.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/increase")
    public Product increaseStock(@PathVariable Long id, @RequestParam int quantity) {
        return service.increaseStock(id, quantity);
    }

    @PostMapping("/{id}/decrease")
    public Product decreaseStock(@PathVariable Long id, @RequestParam int quantity) {
        return service.decreaseStock(id, quantity);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return service.getLowStockProducts();
    }
}
