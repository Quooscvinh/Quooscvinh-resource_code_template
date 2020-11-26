package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Supplier;

public interface SupplierService {
	List<Supplier> getAllSupplier();
	
	void saveSupplier(Supplier supplier);
	
	void deleteSupplier(Integer supplierID);
	
	Optional<Supplier> findSupplierById(Integer supplierID);

	Page<Supplier> findPaginated(int pageNo, int pageSize);

	Page<Supplier> findPaginated1(String supplierName, int pageNo, int pageSize);

}
