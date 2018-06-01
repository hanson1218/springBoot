package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.entity.Product;
import com.lhs.www.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/insert")
	@GetMapping
	public String insert(@RequestBody Product pruduct) {
		int flag = productService.insert(pruduct);
		if (flag>0) {
			return "insert successful";
		}
		return "failed";
	}
}
