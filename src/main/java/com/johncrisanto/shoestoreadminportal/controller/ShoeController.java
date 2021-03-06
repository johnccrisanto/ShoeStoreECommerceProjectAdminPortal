package com.johncrisanto.shoestoreadminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/remove")
	public String remove(@ModelAttribute("id") String id, Model model) {
			shoeService.removeById(Long.parseLong(id.substring(8)));
			List<Shoe> shoeList = shoeService.findAll();
			model.addAttribute("shoeList", shoeList);
			
			return "redirect:/shoe/shoeList";
			
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
	
	@GetMapping("/updateShoe")
	public String updateShoe(@RequestParam("id") Long id, Model model) {
		
		Shoe shoe = shoeService.findById(id);
		
		List<String> shoeBrands = new ArrayList<>();
		shoeBrands.add("Adidas");
		shoeBrands.add("ASICS");
		shoeBrands.add("New Balance");
		shoeBrands.add("Nike");
		shoeBrands.add("Puma");
		shoeBrands.add("Reebok");
		
		List<String> shoeCategories = new ArrayList<>();
		shoeCategories.add("Basketball");
		shoeCategories.add("Running");
		shoeCategories.add("Tennis");
		
		model.addAttribute("shoe", shoe);
		model.addAttribute("shoeBrands", shoeBrands);
		model.addAttribute("shoeCategories", shoeCategories);
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
