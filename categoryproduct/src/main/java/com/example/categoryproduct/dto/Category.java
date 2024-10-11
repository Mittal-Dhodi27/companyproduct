package com.example.categoryproduct.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String c_description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private List<Product> product=new ArrayList<>();
	
	public void addProduct(Product p) {
        product.add(p);
    }
	
}
