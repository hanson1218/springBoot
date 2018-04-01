package com.lhs.www.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(value=Exception.class)
	@ResponseBody
	private Map<String,Object> exceptionHandler(HttpServletRequest req,Exception e){
		Map<String, Object> map = new HashMap<>();
		map.put("success", false);
		map.put("errorMsg", e.getMessage());
		return map;
	}
}
