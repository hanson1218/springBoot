package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.entity.Order;
import com.lhs.www.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/insert")
	@PostMapping
	public String insertOrder(@RequestBody Order order) {
		int flag = orderService.insert(order);
		if (flag>0) {
			return "insert successFul";
		}
		return "insert failed";
	}
}
