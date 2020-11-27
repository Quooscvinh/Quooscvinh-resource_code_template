package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Supplier;
import com.beebrick.repository.SupplierRepository;
import com.beebrick.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> getAllSupplier() {
		return supplierRepository.findAll();
	}
	
	@Override
	public void saveSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(Integer supplierID) {
		supplierRepository.deleteById(supplierID);;
	}
	
	@Override
	public Optional<Supplier> findSupplierById(Integer supplierID) {
		return supplierRepository.findById(supplierID);
	}
	
	@Override
	public Page<Supplier> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return supplierRepository.getAllSupplier(pageable);
	}

	@Override
	public Page<Supplier> findPaginated1(String username, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
