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

import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.services.ProductService;
import com.example.categoryproduct.util.APIResponse;
import com.example.categoryproduct.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ApiOperation(notes = "This api is used to save hospital data into database",value = "Save Product API")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "Data Saved Successfully")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam int id)
	{
		return productService.getProductById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam int id,@RequestBody Product product)
	{
		return productService.updateProduct(id,product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int id)
	{
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllData()
	{
		return productService.getAllData();
	}
	
	@GetMapping("/{field}")
	public APIResponse<List<Product>> GetProductWithSorting(@PathVariable String field){
		List<Product> allProduct=productService.findProductWithSorting(field);
		return new APIResponse<List<Product>>(allProduct.size(),allProduct);
	}
	

	@GetMapping("/pagination/{offset}/{pageSize}")
	public APIResponse<Page<Product>> findProductWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
		Page<Product> productWithPagination=productService.findProductWithPagination(offset, pageSize);
		return new APIResponse<>(productWithPagination.getSize(),productWithPagination);
	}
	
}
