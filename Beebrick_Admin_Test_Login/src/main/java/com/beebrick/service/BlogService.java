package com.beebrick.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Blog;

public interface BlogService {

	void save(Blog blog);

	void delete(Integer blogID);

	Optional<Blog> findById(Integer blogID);

	Page<Blog> findPaginated(int pageNo, int pageSize);
}
