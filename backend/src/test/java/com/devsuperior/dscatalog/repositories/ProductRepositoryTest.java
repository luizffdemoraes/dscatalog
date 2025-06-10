package com.devsuperior.dscatalog.repositories;

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

    @Test
    void deleteShouldDeleteObjectWhenIdExists() {

        long existingID = 1L;

        repository.deleteById(existingID);
        Optional<Product> result = repository.findById(existingID);
        Assertions.assertFalse(result.isPresent());
    }

}