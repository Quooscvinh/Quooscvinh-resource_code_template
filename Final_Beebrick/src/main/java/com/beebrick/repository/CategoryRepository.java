package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beebrick.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query(value = "SELECT * FROM category", nativeQuery = true)
	public Page<Category> getAllCategory(Pageable pageable);
}
