package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.Factory;
import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long countTotalProduct;

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        countTotalProduct = 25;
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProduct+1, product.getId());
    }

    @Test
    void deleteShouldDeleteObjectWhenIdExists() {

        existingId = 1L;

        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }
}