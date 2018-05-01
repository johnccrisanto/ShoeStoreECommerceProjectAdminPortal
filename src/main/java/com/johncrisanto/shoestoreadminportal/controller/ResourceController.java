package com.johncrisanto.shoestoreadminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johncrisanto.shoestoreadminportal.service.ShoeService;

@RestController
public class ResourceController {

	@Autowired
	private ShoeService shoeService;
	
	@PostMapping("/shoe/removeList")
	public String removeList(@RequestBody ArrayList<String> shoeIDList, Model model) {
		for (String id: shoeIDList) {
			String shoeId = id.substring(8);
			shoeService.removeById(Long.parseLong(shoeId));
		}
		
		return "delete success";
	}
}
