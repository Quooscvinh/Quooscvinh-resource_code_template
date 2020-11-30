package com.beebrick.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Blog;
import com.beebrick.repository.BlogRepository;
import com.beebrick.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public void save(Blog blog) {
		blogRepository.save(blog);
	}

	@Override
	public void delete(Integer blogID) {
		blogRepository.delete(blogID);
	}

	@Override
	public Optional<Blog> findById(Integer blogID) {
		return blogRepository.findById(blogID);
	}

	@Override
	public Page<Blog> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return blogRepository.getAllBlog(pageable);
	}
}
