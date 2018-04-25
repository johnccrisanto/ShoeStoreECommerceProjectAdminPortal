package com.johncrisanto.shoestoreadminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.johncrisanto.shoestoreadminportal.entity.Shoe;
import com.johncrisanto.shoestoreadminportal.service.ShoeService;

@Controller
@RequestMapping("/shoe")
public class ShoeController {
	
	@Autowired
	private ShoeService shoeService;
	
	
	
	@RequestMapping("/add")
	public String addShoePage(Model model) {
		
		model.addAttribute("shoe", new Shoe());
		return "addShoe";
	}

	@PostMapping("/add")
	public String addShoePagePost(@ModelAttribute("shoe") Shoe shoe, HttpServletRequest httpServletRequest) {
		
	shoeService.save(shoe);
	
	MultipartFile shoeImage = shoe.getImage();
	
	System.out.println(shoeImage.getOriginalFilename());
	
	try {
		byte[] bytes = shoeImage.getBytes();
		String name = shoe.getId() + ".png";
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(("src/main/resources/static/images/shoe/" + name))));
		stream.write(bytes);;
		stream.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return "redirect:shoeList";
	}
	
	@RequestMapping("/shoeList")
	public String bookList(Model model) {
		
		List<Shoe> shoeList = shoeService.findAll();
		
		model.addAttribute("shoeList", shoeList);
		return "shoeList";
	}
	
	@RequestMapping("/shoeInfo")
	public String shoeInfo(@RequestParam("id") Long id, Model model) {
		Shoe shoe = shoeService.findById(id);
		model.addAttribute("shoe", shoe);
		return "shoeInfo";
	}
	
	@RequestMapping("/updateShoe")
	public String updateShoe(@RequestParam("id") Long id, Model model) {
		
		Shoe shoe = shoeService.findById(id);
		
		model.addAttribute("shoe", shoe);
		return "updateShoe";
	}
	
	@PostMapping("/updateShoe") 
	public String updateShoePost(@ModelAttribute("shoe") Shoe shoe, HttpServletRequest request) {
		
		shoeService.save(shoe);
		
		MultipartFile shoeImage = shoe.getImage();
		
		if(!shoeImage.isEmpty()) {
			try {
				byte[] bytes = shoeImage.getBytes();
				String name = shoe.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/images/shoe/" + name));
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(("src/main/resources/static/images/shoe/" + name))));
				stream.write(bytes);;
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return "redirect:/shoe/shoeList";
	}
}
