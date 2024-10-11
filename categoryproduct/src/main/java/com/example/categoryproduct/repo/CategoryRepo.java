package com.example.categoryproduct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.categoryproduct.dto.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
