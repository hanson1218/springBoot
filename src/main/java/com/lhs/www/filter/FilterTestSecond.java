package com.lhs.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.MDC;
import org.springframework.core.annotation.Order;

@Order(20)
@WebFilter(filterName = "testFilterSecond", urlPatterns = {"/envs","/user/*"})
public class FilterTestSecond implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("TestFilter2");
	    filterChain.doFilter(servletRequest,servletResponse);
	    System.out.println(MDC.get("test"));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
