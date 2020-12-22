package com.lhs.www.controller;

import com.lhs.www.entity.User;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {
	
	@Value("${test.env}")
	private String env;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name){
		List<String> list = new ArrayList<String>();
		HashMap<String, User> userHashMap = new HashMap<>();
		int a = 1+1;
		while (a==2){
			list.add("1");

		}

		for (int i=0; i<Integer.MAX_VALUE;i++){
			userHashMap.put(i+"",new User());
		}
		return "Hello1:" + name;
	}
	
	@RequestMapping("/envs")
	public String testMultiEnv(){
		System.out.println("controler");
		return env;
	}
	
}
