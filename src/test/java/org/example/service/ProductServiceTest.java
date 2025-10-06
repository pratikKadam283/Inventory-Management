package org.example.service;


import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    public void testIncreaseStock() {
        Product product = new Product("Test", "Desc", 5, 10);
        product.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(product);

        Product updated = service.increaseStock(1L, 5);
        assertEquals(10, updated.getStockQuantity());
    }

    @Test
    public void testDecreaseStockThrowsErrorIfInsufficient() {
        Product product = new Product("Test", "Desc", 3, 10);
        product.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () -> service.decreaseStock(1L, 5));
    }
}
