package com.johncrisanto.shoestoreadminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String indexPage() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String homePage() {
		return "home";
	}
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/processLogout")
	public String logout(Model model) {
		model.addAttribute("logoutSuccessful", true);
		return "login";
	}
	
	

}
