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

import com.beebrick.entity.Role;
import com.beebrick.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("admin/role/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 20;

		Page<Role> page = roleService.findPaginated(pageNo, pageSize);
		List<Role> roles = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("roles", roles);
		return "admin/role/index";
	}

	@RequestMapping("admin/role")
	public String index(Model model) {
		return "redirect:/admin/role/page/1";
	}

	@RequestMapping(value = "admin/role/add")
	public String add(Model model) {
		model.addAttribute("role", new Role());
		return "admin/role/add";
	}

	@RequestMapping(value = "admin/role/save", method = RequestMethod.POST)
	public String save(@Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/role/add";
		} else {
			roleService.save(role);
			return "redirect:/admin/role/page/1";
		}
	}

	@RequestMapping(value = "admin/role/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("roleID") Integer roleID, Model model) {
		Optional<Role> edit = roleService.findById(roleID);
		edit.ifPresent(role -> model.addAttribute("role", role));
		return "admin/role/edit";
	}
	
	@RequestMapping(value = "admin/role/update", method = RequestMethod.POST)
	public String update(@Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/role/edit";
		} else {
			roleService.save(role);
			return "redirect:/admin/role/page/1";
		}
	}

	@RequestMapping(value = "admin/role/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("roleID") Integer roleID, Model model) {
		roleService.delete(roleID);
		return "redirect:/admin/role/page/1";
	}
}
