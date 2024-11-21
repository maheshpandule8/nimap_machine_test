package com.app.service;
import com.app.model.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository; // To handle category association

    public Page<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProductById(Long id, Product productDetails) throws AotInitializerNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow();

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        if (productDetails.getCategory() != null) {
            Long categoryId = productDetails.getCategory().getId();
            categoryRepository.findById(categoryId)
                    .orElseThrow();
            product.setCategory(productDetails.getCategory());
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) throws AotInitializerNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow();

        productRepository.delete(product);
    }
}

