package com.example.categoryproduct.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.categoryproduct.dto.Category;
import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.repo.CategoryRepo;

@Repository
public class CategoryDao {
	
	@Autowired
	private CategoryRepo categoryRepo;

	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	public Category getById(int id) {
		Optional<Category> optional=categoryRepo.findById(id);
	
		if (optional!=null) {
			return optional.get();
		}
		return null;
	}

	public Category updateCategory(int id, Category category) {
		Optional<Category> optional=categoryRepo.findById(id);
		if (optional.isPresent()) {	
			category.setId(id);
			return categoryRepo.save(category);
		}
		return null;	}

	public Category deleteCategory(int id) {
		Optional<Category> optional=categoryRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Category category=optional.get();
		categoryRepo.delete(category);
		return category;
	}

	public List<Category> getAll() {
		return categoryRepo.findAll();
	}

	public List<Category> findCategoryWithSorting(String field)
	{
		return categoryRepo.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	
	public Page<Category> findCategoryWithPagination(int offset, int pageSize) {
		Page<Category> categories=categoryRepo.findAll(PageRequest.of(offset, pageSize));
		return categories;
		
	}
	
}
