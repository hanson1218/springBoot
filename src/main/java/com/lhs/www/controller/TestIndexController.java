package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.entity.TestIndex;
import com.lhs.www.service.TestIndexService;

@RestController
@RequestMapping("/testindex")
public class TestIndexController {

	@Autowired
	private TestIndexService testIndexService;
	
	@GetMapping
	@RequestMapping("/add")
	public String addTestIndex(TestIndex testIndex) {
		testIndexService.insert(testIndex);
		return "success";
	}
}
