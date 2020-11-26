package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Category;
import com.beebrick.repository.CategoryRepository;
import com.beebrick.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public void deleteCategory(Integer categoryID) {
		categoryRepository.deleteById(categoryID);
		
	}

	@Override
	public Optional<Category> findSupplierById(Integer categoryID) {
		return categoryRepository.findById(categoryID);
	}

	@Override
	public Page<Category> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return categoryRepository.getAllCategory(pageable);
	}

	@Override
	public Page<Category> findPaginated1(String categoryName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
