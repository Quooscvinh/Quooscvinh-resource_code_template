package com.beebrick.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;

import com.beebrick.entity.Category;
import com.beebrick.entity.Manufacturer;
import com.beebrick.entity.Product;
import com.beebrick.service.CategoryService;
import com.beebrick.service.ManufacturerService;
import com.beebrick.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ManufacturerService manufacturerService;

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
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		List<Manufacturer> manufacturers = manufacturerService.findAll();
		model.addAttribute("manufacturers", manufacturers);
		
		model.addAttribute("product", new Product());
		return "admin/product/add";
	}
	
	@RequestMapping(value = "admin/product/save", method = RequestMethod.POST)
	public String save(@Valid Product product, BindingResult bindingResult, @RequestParam("photo_file") MultipartFile photo) {
		if (bindingResult.hasErrors()) {
			return "admin/product/add";
		} else {
			try {
				Path path = Paths.get("uploads/");
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				product.setImage(photo.getOriginalFilename().toLowerCase());
			} catch (Exception e) {
			}
			productService.save(product);;
			return "redirect:/admin/product/page/1";
		}
	}

	@RequestMapping(value = "admin/product/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("productID") Integer productID, Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		List<Manufacturer> manufacturers = manufacturerService.findAll();
		model.addAttribute("manufacturers", manufacturers);
		
		Optional<Product> edit = productService.findById(productID);
		edit.ifPresent(product -> model.addAttribute("product", product));
		return "admin/product/edit";
	}

	@RequestMapping(value = "admin/product/update", method = RequestMethod.POST)
	public String update(@Valid Product product, BindingResult bindingResult, @RequestParam("photo_file") MultipartFile photo) {
		if (bindingResult.hasErrors()) {
			return "admin/product/add";
		} else {
			try {
				Path path = Paths.get("uploads/");
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				product.setImage(photo.getOriginalFilename().toLowerCase());
			} catch (Exception e) {
			}
			productService.save(product);;
			return "redirect:/admin/product/page/1";
		}
	}

	@RequestMapping(value = "admin/product/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("productID") Integer productID, Model model) {
		productService.delete(productID);
		return "redirect:/admin/product/page/1";
	}
}
