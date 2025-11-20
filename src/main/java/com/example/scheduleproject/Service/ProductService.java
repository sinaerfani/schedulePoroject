package com.example.scheduleproject.Service;

import com.example.scheduleproject.Repository.ProductRepository;
import com.example.scheduleproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository ;


    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.orElseThrow(()-> new RuntimeException("file.not.found"));

    }
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
