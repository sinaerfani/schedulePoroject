package com.example.scheduleproject.Repository;

import com.example.scheduleproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
