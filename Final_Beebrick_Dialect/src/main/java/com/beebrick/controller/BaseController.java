package com.beebrick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
	
	@RequestMapping(value = { "/login", "/" })
	public String login(@RequestParam(required=false) String message, final Model model) {
		if (message != null && !message.isEmpty()) {
			if (message.equals("timeout")) {
				model.addAttribute("message", "Time out");
			}
			if (message.equals("max_session")) {
				model.addAttribute("message", "This accout has been login from another device!");
			}
			if (message.equals("logout")) {
				model.addAttribute("message", "Logout!");
			}
			if (message.equals("error")) {
				model.addAttribute("message", "Login Failed!");
			}
		}
		return "login";
	}

	@RequestMapping("admin/user/list")
	public String user() {
		return "redirect:/admin/user/page/1";
	}

}
