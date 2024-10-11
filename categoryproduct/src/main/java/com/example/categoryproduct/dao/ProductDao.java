package com.example.categoryproduct.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.categoryproduct.dto.Product;
import com.example.categoryproduct.repo.ProductRepo;

import java.util.List;
import java.util.Optional;


@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepo productRepo;
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	public Product getProductById(int id) {
		Optional<Product> optional=productRepo.findById(id);
		if (optional!=null) {
			return optional.get();
		}
		return null;
	}

	

	public Product updateProduct(int id, Product product) {
		Optional<Product> optional=productRepo.findById(id);
		if (optional.isPresent()) {	
			product.setId(id);
			return productRepo.save(product);
		}
		return null;
		
	}

	public Product deleteProduct(int id) {
		Optional<Product> optional=productRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Product product=optional.get();
		productRepo.delete(product);
		return product;
		
	}

	public List<Product> getAllData() {
		return productRepo.findAll();
	
	}
	
	public List<Product> findProductWithSorting(String field)
	{
		return productRepo.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	

	public Page<Product> findProductWithPagination(int offset, int pageSize) {
		Page<Product> products=productRepo.findAll(PageRequest.of(offset, pageSize));
		return products;
		
	}
	
	
	

}
