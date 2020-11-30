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

import com.beebrick.entity.Manufacturer;
import com.beebrick.service.ManufacturerService;

@Controller
public class ManufacturerController {
	@Autowired
	private ManufacturerService manufacturerService;

	@GetMapping("admin/manufacturer/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		
		Page<Manufacturer> page = manufacturerService.findPaginated(pageNo, pageSize);
		List<Manufacturer> manufacturers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("manufacturers", manufacturers);
		return "admin/manufacturer/index";
	}

	@RequestMapping("admin/manufacturer")
	public String index(Model model) {
		return "redirect:/admin/manufacturer/page/1";
	}

	@RequestMapping(value = "admin/manufacturer/add")
	public String add(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		return "admin/manufacturer/add";
	}
	
	@RequestMapping(value = "admin/manufacturer/save", method = RequestMethod.POST)
	public String save(@Valid Manufacturer manufacturer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/manufacturer/add";
		} else {
			manufacturerService.save(manufacturer);
			return "redirect:/admin/manufacturer/page/1";
		}
	}

	@RequestMapping(value = "admin/manufacturer/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("manufacturerID") Integer manufacturerID, Model model) {
		Optional<Manufacturer> edit = manufacturerService.findById(manufacturerID);
		edit.ifPresent(manufacturer -> model.addAttribute("manufacturer", manufacturer));
		return "admin/manufacturer/edit";
	}

	@RequestMapping(value = "admin/manufacturer/update", method = RequestMethod.POST)
	public String update(@Valid Manufacturer manufacturer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/manufacturer/edit";
		} else {
			manufacturerService.save(manufacturer);
			return "redirect:/admin/manufacturer/page/1";
		}
	}

	@RequestMapping(value = "admin/manufacturer/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("manufacturerID") Integer manufacturerID, Model model) {
		manufacturerService.delete(manufacturerID);
		return "redirect:/admin/manufacturer/page/1";
	}
}
