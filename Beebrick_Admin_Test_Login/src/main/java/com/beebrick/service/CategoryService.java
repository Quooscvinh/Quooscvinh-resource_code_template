package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Category;

public interface CategoryService {
	
	List<Category> findAll();

	void save(Category category);

	void delete(Integer categoryID);

	Optional<Category> findById(Integer categoryID);

	Page<Category> findPaginated(int pageNo, int pageSize);
}
