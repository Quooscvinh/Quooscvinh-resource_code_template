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

import com.beebrick.entity.Category;
import com.beebrick.entity.Supplier;
import com.beebrick.service.CategoryService;
import com.beebrick.service.SupplierService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("admin/category/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		
		Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
		List<Category> categories = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("categories", categories);
		return "admin/category/index";
	}

	@RequestMapping("admin/category")
	public String index(Model model) {

		return "redirect:/admin/category/page/1";
	}

	@RequestMapping(value = "admin/category/add")
	public String add(Model model) {
		
		model.addAttribute("category", new Category());
		return "admin/category/add";
	}

	@RequestMapping(value = "admin/category/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("categoryID") Integer categoryID, Model model) {
		
		Optional<Category> categoryEdit = categoryService.findSupplierById(categoryID);
		categoryEdit.ifPresent(category -> model.addAttribute("category", category));
		return "admin/category/edit";
	}

	@RequestMapping(value = "admin/category/save", method = RequestMethod.POST)
	public String save(@Valid Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/category/add";
		} else {
			categoryService.saveCategory(category);
			return "redirect:/admin/category/page/1";
		}
	}

	@RequestMapping(value = "admin/category/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("categoryID") Integer categoryID, Model model) {
		categoryService.deleteCategory(categoryID);
		return "redirect:/admin/category/page/1";
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
