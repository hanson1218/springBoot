package com.lhs.www.initConfig;

import java.beans.PropertyVetoException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;
 
@Configuration
// TODO 配置mybatis 的mapper 扫描路径
@MapperScan("com.lhs.www.dao")
public class DataSourceConfiguration {
	
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.userName}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	@Bean(name="dataSource")
	public ComboPooledDataSource createDataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(jdbcDriver);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		//关闭连接后不自动commit
		dataSource.setAutoCommitOnClose(false);
		return dataSource;
	}
}
