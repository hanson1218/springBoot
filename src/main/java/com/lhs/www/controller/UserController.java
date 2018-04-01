package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.entity.User;
import com.lhs.www.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/{id}")
	@GetMapping
	public String getUserInfoById(@PathVariable String id){
		return userService.getUserInfoById(id).toString();
	}
	
	@RequestMapping("/adduser")
	@GetMapping
	public String getUserInfoById(@RequestBody User user){
		 boolean flag  = userService.insertUser(user);
		return flag ? "success":"failed";
	}
}
