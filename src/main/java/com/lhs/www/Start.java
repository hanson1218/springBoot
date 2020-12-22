package com.lhs.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//重点(添加filter)
@ServletComponentScan
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}

}
