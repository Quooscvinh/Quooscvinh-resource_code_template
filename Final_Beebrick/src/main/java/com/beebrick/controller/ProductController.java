package com.beebrick.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beebrick.entity.Category;
import com.beebrick.entity.Product;
import com.beebrick.entity.Supplier;
import com.beebrick.service.CategoryService;
import com.beebrick.service.ProductService;
import com.beebrick.service.SupplierService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;

	@GetMapping("admin/product/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize);
		List<Product> products = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("products", products);
		return "admin/product/index";
	}

	@RequestMapping("admin/product")
	public String index(Model model) {

		return "redirect:/admin/product/page/1";
	}

	@RequestMapping(value = "admin/product/add")
	public String add(Model model) {
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		
		List<Supplier> suppliers = supplierService.getAllSupplier();
		model.addAttribute("suppliers", suppliers);
		
		model.addAttribute("product", new Product());
		return "admin/product/add";
	}

	@RequestMapping(value = "admin/product/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("productID") Integer productID, Model model) {
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		
		List<Supplier> suppliers = supplierService.getAllSupplier();
		model.addAttribute("suppliers", suppliers);
		
		Optional<Product> productEdit = productService.findProductById(productID);
		productEdit.ifPresent(product -> model.addAttribute("product", product));
		return "admin/product/edit";
	}

	@RequestMapping(value = "admin/product/save", method = RequestMethod.POST)
	public String save(@Valid Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/product/add";
		} else {
			productService.saveProduct(product);;
			return "redirect:/admin/product/page/1";
		}
	}

	@RequestMapping(value = "admin/product/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("productID") Integer productID, Model model) {
		productService.deleteProduct(productID);
		return "redirect:/admin/product/page/1";
	}

	@GetMapping("admin/product/search/page/{pageNo}")
	public String findPaginatedSearch(@Valid @PathVariable(value = "pageNo") int pageNo,
			@Param("keyword") String keyword, Model model) {

		int pageSize = 10;
		if (keyword == "") {
			return findPaginated(1, model);
		}

		Page<Product> page = productService.findPaginated1(keyword, pageNo, pageSize);

		List<Product> products = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("products", products);
		return "admin/product/index";

	}

}
