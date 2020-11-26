package com.beebrick.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Product;

public interface ProductService {

	void saveProduct(Product product);
	
	void deleteProduct(Integer productID);

	Optional<Product> findProductById(Integer productID);

	Page<Product> findPaginated(int pageNo, int pageSize);

	Page<Product> findPaginated1(String productName, int pageNo, int pageSize);
}
