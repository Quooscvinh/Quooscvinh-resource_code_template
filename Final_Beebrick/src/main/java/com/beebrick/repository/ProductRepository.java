package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "SELECT * FROM product WHERE Status = 0", nativeQuery = true)
	public Page<Product> getAllProduct(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET Status = 1 WHERE ProductID=?1", nativeQuery = true)
	void deleteProduct(Integer productID);
	
	@Query(value = "SELECT * FROM product WHERE ProductName LIKE %?1% and Status = 0", nativeQuery = true)
	public Page<Product> searchProduct(String productName, Pageable pageable);
}
