package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
	
	@Query(value = "SELECT * FROM manufacturer WHERE IsActive = 0", nativeQuery = true)
	public Page<Manufacturer> getAllManufacturer(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE manufacturer SET IsActive = 1 WHERE ManufacturerID=?1", nativeQuery = true)
	void delete(Integer manufacturerID);
}
