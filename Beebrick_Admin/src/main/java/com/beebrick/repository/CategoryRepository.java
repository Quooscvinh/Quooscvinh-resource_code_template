package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category WHERE IsActive = 0", nativeQuery = true)
	public Page<Category> getAllCategory(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET IsActive = 1 WHERE CategoryID=?1", nativeQuery = true)
	void delete(Integer categoryID);
}
