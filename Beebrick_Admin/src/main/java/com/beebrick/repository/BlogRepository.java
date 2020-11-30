package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	@Query(value = "SELECT * FROM blog WHERE IsActive = 0", nativeQuery = true)
	public Page<Blog> getAllBlog(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE blog SET IsActive = 1 WHERE BlogID=?1", nativeQuery = true)
	void delete(Integer blogID);
}
