package com.beebrick.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT * FROM users WHERE Status = 0", nativeQuery = true)
	public Page<User> getAllUser(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET Status = 1 WHERE UserID=?1", nativeQuery = true)
	void deleteUser(Integer userID);
	
	@Query(value = "SELECT * FROM users WHERE Username LIKE %?1% and Status = 0", nativeQuery = true)
	public Page<User> searchUser(String username, Pageable pageable);
	
	@Query(value = "SELECT * FROM users WHERE Username LIKE %?1% and Status = 0", nativeQuery = true)
	public List<User> search1(String pageable);
}
