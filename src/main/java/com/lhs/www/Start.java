package com.lhs.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//重点(添加filter)
@ServletComponentScan
@EnableCaching// 开启缓存，需要显示的指定
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}

}
