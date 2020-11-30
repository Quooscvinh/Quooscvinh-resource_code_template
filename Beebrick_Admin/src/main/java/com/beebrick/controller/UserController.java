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

import com.beebrick.entity.Role;
import com.beebrick.entity.User;
import com.beebrick.service.RoleService;
import com.beebrick.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	@GetMapping("admin/user/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 20;

		Page<User> page = userService.findPaginated(pageNo, pageSize);
		List<User> users = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("users", users);
		return "admin/user/index";
	}

	@RequestMapping("admin/user")
	public String index(Model model) {
		return "redirect:/admin/user/page/1";
	}

	@RequestMapping(value = "admin/user/add")
	public String add(Model model) {
		List<Role> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new User());
		return "admin/user/add";
	}
	
	@RequestMapping(value = "admin/user/save", method = RequestMethod.POST)
	public String save(@Valid User user, BindingResult bindingResult, @RequestParam("photo_file") MultipartFile photo) {
		if (bindingResult.hasErrors()) {
			return "admin/user/add";
		} else {
			try {
				Path path = Paths.get("uploads/");
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				user.setImage(photo.getOriginalFilename().toLowerCase());
			} catch (Exception e) {
			}
			userService.save(user);		
			return "redirect:/admin/user/page/1";
		}
	}

	@RequestMapping(value = "admin/user/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("userID") Integer userID, Model model) {
		List<Role> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		Optional<User> edit = userService.findById(userID);
		edit.ifPresent(user -> model.addAttribute("user", user));
		return "admin/user/edit";
	}
	
	@RequestMapping(value = "admin/user/update", method = RequestMethod.POST)
	public String update(@Valid User user, BindingResult bindingResult, @RequestParam("photo_file") MultipartFile photo) {
		if (bindingResult.hasErrors()) {
			return "admin/user/add";
		} else {
			try {
				Path path = Paths.get("uploads/");
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				user.setImage(photo.getOriginalFilename().toLowerCase());
			} catch (Exception e) {
			}
			userService.save(user);		
			return "redirect:/admin/user/page/1";
		}
	}

	@RequestMapping(value = "admin/user/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("userID") Integer userID, Model model) {
		userService.delete(userID);
		return "redirect:/admin/user/page/1";
	}

//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public String find(@Param("keyword") String keyword, Model model) {
//		if (keyword == "") {
//			return findPaginated(1, model);
//		} else {
//			List<User> user = userService.listAll(keyword);
//			model.addAttribute("users", user);
//			return "admin/user/index";
//		}
////		return "search";
//	}

//	@GetMapping("admin/user/search/page/{pageNo}")
//	public String findPaginatedSearch(@Valid @PathVariable(value = "pageNo") int pageNo,
//			@Param("keyword") String keyword, Model model) {
//
//		int pageSize = 1;
//		if (keyword == "") {
//			return findPaginated(1, model);
//		}
//
//		Page<User> page = userService.findPaginated1(keyword, pageNo, pageSize);
//
//		List<User> users = page.getContent();
//
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("users", users);
//		return "admin/user/index";
//
//	}

}
