package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Manufacturer;

public interface ManufacturerService {
	
	List<Manufacturer> findAll();
	
	void save(Manufacturer manufacturer);
	
	void delete(Integer manufacturerID);
	
	Optional<Manufacturer> findById(Integer manufacturerID);

	Page<Manufacturer> findPaginated(int pageNo, int pageSize);
}
