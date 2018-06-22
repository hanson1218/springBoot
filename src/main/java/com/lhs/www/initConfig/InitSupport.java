package com.lhs.www.initConfig;

import javax.servlet.ServletContext;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.lhs.www.initConfig.initServlet.buildInfo.BuildInfoServlet;

@Configuration
@EnableConfigurationProperties
public class InitSupport implements ServletContextAware{

	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
//		Validate.notNull(servletContext, "servletContext is required");
	    this.servletContext = servletContext;
		
	}
	
	@Bean
	  public ServletRegistrationBean registBuildInfo() {
	    final ServletRegistrationBean buildInfoBean =
	        new ServletRegistrationBean(new BuildInfoServlet(), "/buildInfo");
	    buildInfoBean.setName("buildInfo");
	    return buildInfoBean;
	  }

}
