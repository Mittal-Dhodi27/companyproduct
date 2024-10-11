package com.example.categoryproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.categoryproduct.dto.Category;
import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.services.CategoryService;
import com.example.categoryproduct.util.APIResponse;
import com.example.categoryproduct.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation(notes = "This api is used to save hospital data into database",value = "Save Product API")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "Data Saved Successfully")})
	@PostMapping("/save")
	public Category saveCategory(@RequestBody Category category)
	{
		System.out.println("Category"+category);
		return categoryService.saveCategory(category);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Category>> getById(@RequestParam int id)
	{
		return categoryService.getById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Category>> updateCategory(@RequestParam int id,@RequestBody Category category)
	{
		return categoryService.updateProduct(id,category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(@PathVariable int id)
	{
		return categoryService.deleteCategory(id);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Category>>> getAll()
	{
		return categoryService.getAll();
	}
	
	@GetMapping("/{field}")
	public APIResponse<List<Category>> GetCategoryWithSorting(@PathVariable String field){
		List<Category> allCategory=categoryService.findCategoryWithSorting(field);
		return new APIResponse<List<Category>>(allCategory.size(),allCategory);
	}
	

	@GetMapping("/pagination/{offset}/{pageSize}")
	public APIResponse<Page<Category>> findCategoryWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
		Page<Category> categoryWithPagination=categoryService.findCategoryWithPagination(offset, pageSize);
		return new APIResponse<>(categoryWithPagination.getSize(),categoryWithPagination);
	}
	
}
