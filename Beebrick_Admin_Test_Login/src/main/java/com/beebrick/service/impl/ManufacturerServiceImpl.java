package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Manufacturer;
import com.beebrick.repository.ManufacturerRepository;
import com.beebrick.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public void save(Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
	}

	@Override
	public void delete(Integer manufacturerID) {
		manufacturerRepository.delete(manufacturerID);
	}

	@Override
	public Optional<Manufacturer> findById(Integer manufacturerID) {
		return manufacturerRepository.findById(manufacturerID);
	}

	@Override
	public Page<Manufacturer> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return manufacturerRepository.getAllManufacturer(pageable);
	}

	
}
