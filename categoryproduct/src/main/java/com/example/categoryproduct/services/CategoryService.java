package com.example.categoryproduct.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.categoryproduct.dao.CategoryDao;
import com.example.categoryproduct.dao.ProductDao;
import com.example.categoryproduct.dto.Category;
import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.util.ResponseStructure;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao dao;
	@Autowired
	private ProductDao productDao;
	
	
	public Category saveCategory(Category category) {
		System.out.println("Category"+category);
		Category c = new Category();
        c.setName(category.getName());
        c.setC_description(category.getC_description());
        
        for (var pro : category.getProduct()) {
            Product product = new Product(pro.getName(),pro.getPrice());
            c.addProduct(product);
        }
        
       return dao.saveCategory(c);
//        
//		ResponseStructure<Category> structure=new ResponseStructure<Category>();
//		structure.setMessage("Product save..!!!");
//		structure.setStatus(HttpStatus.CREATED.value());
//		structure.setData(dao.saveCategory(category));
//		
//		return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Category>> getById(int id) {
		Category category=dao.getById(id);
		ResponseStructure<Category> structure=new ResponseStructure<Category>();
		
		if (category!=null) {
			structure.setMessage("Category Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(category);
			
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Category>> updateProduct(int id, Category category) {
		

		ResponseStructure<Category> structure=new ResponseStructure<Category>();
		Category c=dao.updateCategory(id,category);
		if (c !=null) {
			structure.setMessage("Hospital saved Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveCategory(category));
			
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("No Data Present");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(c);
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.NOT_FOUND);
		}
	
		
	}

	public ResponseEntity<ResponseStructure<Category>> deleteCategory(int id) {

		Category category=dao.deleteCategory(id);
		ResponseStructure<Category> structure=new ResponseStructure<Category>();
		if (category!=null) {
			
			structure.setMessage("Data Delete Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(category);
			
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.OK);
		} else {
			
			structure.setMessage("Product Data Not Found..!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.equals(category);
			
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Category>>> getAll() {
		List<Category> list=dao.getAll();
		ResponseStructure<List<Category>> structure=new ResponseStructure<List<Category>>();
		if (list.isEmpty()) {
			structure.setMessage("Data Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Category>>>(HttpStatus.NOT_FOUND);
			
		} else {
			structure.setMessage("Data FOUND");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
		
			return new ResponseEntity<ResponseStructure<List<Category>>>(HttpStatus.FOUND);
		}
	}


	public List<Category> findCategoryWithSorting(String field) {
		return dao.findCategoryWithSorting(field);
		
	}
	
	public Page<Category> findCategoryWithPagination(int offset, int pageSize) {
		return dao.findCategoryWithPagination(offset,pageSize);
}

}
