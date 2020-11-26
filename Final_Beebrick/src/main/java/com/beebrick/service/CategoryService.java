package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Category;

public interface CategoryService {
	List<Category> getAllCategory();

	void saveCategory(Category category);

	void deleteCategory(Integer categoryID);

	Optional<Category> findSupplierById(Integer categoryID);

	Page<Category> findPaginated(int pageNo, int pageSize);

	Page<Category> findPaginated1(String categoryName, int pageNo, int pageSize);

}
