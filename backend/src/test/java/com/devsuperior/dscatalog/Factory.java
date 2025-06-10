package com.devsuperior.dscatalog;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Product 1", "Description 1", 100.0, "img.png", Instant.parse("2021-01-01T03:00:00Z"));
        product.getCategories().add(new Category(1L, "Category 1"));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
