package com.example.categoryproduct.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.categoryproduct.dao.ProductDao;
import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.util.ResponseStructure;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		structure.setMessage("Product save..!!!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveProduct(product));
		
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
		
		Product product=dao.getProductById(id);
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		
		if (product!=null) {
			structure.setMessage("Product Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id, Product product) {
		
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		Product p=dao.updateProduct(id,product);
		if (p !=null) {
			structure.setMessage("Hospital saved Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveProduct(product));
			
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("No Data Present");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(p);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id) {
		Product product=dao.deleteProduct(id);
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		if (product!=null) {
			
			structure.setMessage("Data Delete Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		} else {
			
			structure.setMessage("Product Data Not Found..!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.equals(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getAllData() {
		List<Product> list=dao.getAllData();
		ResponseStructure<List<Product>> structure=new ResponseStructure<List<Product>>();
		if (list.isEmpty()) {
			
			structure.setMessage("Data Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(HttpStatus.NOT_FOUND);
			
		} else {
			
			structure.setMessage("Data FOUND");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
		
			return new ResponseEntity<ResponseStructure<List<Product>>>(HttpStatus.FOUND);
		}
		
	}
	
	

	public List<Product> findProductWithSorting(String field) {
		return dao.findProductWithSorting(field);
		
	}

	

	public Page<Product> findProductWithPagination(int offset, int pageSize) {
		return dao.findProductWithPagination(offset, pageSize);
	}
}
