package com.lhs.www.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 
	private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{id}")
	@GetMapping
	public String getUserInfoById(@PathVariable String id){
		LOGGER.info("UserId is :"+ id);
		try {
			return userService.getUserInfoById(id).toString()	;
		} catch (Exception e) {
			LOGGER.error("Get UserInfo failed",e);
		}
		return "Get UserInfo failed";
	}
	
	@RequestMapping("/adduser")
	@GetMapping
	public String getUserInfoById(@RequestBody User user){
		 boolean flag  = userService.insertUser(user);
		return flag ? "success":"failed";
	}
	
	
	@RequestMapping("/procedure/{id}")
	@GetMapping
	public String getNameByprocedure(@PathVariable int id){
		return userService.getNameByProcedure(id);
	}
	
	@RequestMapping("/user/{id}")
	@GetMapping
	public String getUserByprocedure(@PathVariable int id){
		return userService.getUserByProcedure(id);
	}
}
