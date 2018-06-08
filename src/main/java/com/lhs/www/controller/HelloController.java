package com.lhs.www.controller;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${test.env}")
	private String env;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name){
		return "Hello1:" + name;
	}
	
	@RequestMapping("/envs")
	public String testMultiEnv(){
		System.out.println("controler");
		return env;
	}
	
}
