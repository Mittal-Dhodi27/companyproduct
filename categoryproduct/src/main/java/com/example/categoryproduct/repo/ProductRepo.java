package com.example.categoryproduct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.categoryproduct.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
