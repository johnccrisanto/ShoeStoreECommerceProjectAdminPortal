package com.johncrisanto.shoestoreadminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johncrisanto.shoestoreadminportal.entity.Shoe;

@Controller
@RequestMapping("/shoe")
public class ShoeController {
	
	@RequestMapping("/add")
	public String addShoePage(Model model) {
		
		model.addAttribute("shoe", new Shoe());
		return "addShoe";
	}

}
