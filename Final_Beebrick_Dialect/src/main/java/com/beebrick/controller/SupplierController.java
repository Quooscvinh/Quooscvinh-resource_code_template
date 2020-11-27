package com.beebrick.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beebrick.entity.Supplier;
import com.beebrick.service.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

	@GetMapping("admin/supplier/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		
		Page<Supplier> page = supplierService.findPaginated(pageNo, pageSize);
		List<Supplier> suppliers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("suppliers", suppliers);
		return "admin/supplier/index";
	}

	@RequestMapping("admin/supplier")
	public String index(Model model) {

		return "redirect:/admin/supplier/page/1";
	}

	@RequestMapping(value = "admin/supplier/add")
	public String add(Model model) {
		
		model.addAttribute("supplier", new Supplier());
		return "admin/supplier/add";
	}

	@RequestMapping(value = "admin/supplier/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("supplierID") Integer supplierID, Model model) {
		
		Optional<Supplier> supplierEdit = supplierService.findSupplierById(supplierID);
		supplierEdit.ifPresent(supplier -> model.addAttribute("supplier", supplier));
		return "admin/supplier/edit";
	}

	@RequestMapping(value = "admin/supplier/save", method = RequestMethod.POST)
	public String save(@Valid Supplier supplier, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/supplier/add";
		} else {
			supplierService.saveSupplier(supplier);
			return "redirect:/admin/supplier/page/1";
		}
	}

	@RequestMapping(value = "admin/supplier/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("supplierID") Integer supplierID, Model model) {
		supplierService.deleteSupplier(supplierID);
		return "redirect:/admin/supplier/page/1";
	}

//	@GetMapping("admin/product/search/page/{pageNo}")
//	public String findPaginatedSearch(@Valid @PathVariable(value = "pageNo") int pageNo,
//			@Param("keyword") String keyword, Model model) {
//
//		int pageSize = 10;
//		if (keyword == "") {
//			return findPaginated(1, model);
//		}
//
//		Page<Product> page = productService.findPaginated1(keyword, pageNo, pageSize);
//
//		List<Product> products = page.getContent();
//
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("products", products);
//		return "admin/supplier/index";
//
//	}

}
