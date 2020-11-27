package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beebrick.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
	@Query(value = "SELECT * FROM supplier", nativeQuery = true)
	public Page<Supplier> getAllSupplier(Pageable pageable);
}
