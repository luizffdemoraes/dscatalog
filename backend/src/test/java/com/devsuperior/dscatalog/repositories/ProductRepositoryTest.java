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

    @Test
    void findShouldFindObjectWhenIdExists() {
        existingId = 1L;
        Product product = Factory.createProduct();
        product.setId(existingId);

        product = repository.save(product);

        repository.findById(product.getId());
        Optional<Product> result = repository.findById(product.getId());
        Assertions.assertEquals(result.get(), product);
    }

    @Test
    void findShouldReturnEmptyWhenIdDoesNotExist() {
        Long nonExistingId = 999L;

        Optional<Product> result = repository.findById(nonExistingId);

        Assertions.assertTrue(result.isEmpty(), "Expected result to be empty when ID does not exist");
    }
}