package com.lhs.www.initConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer{

	@Autowired
	private DataSource dataSource;
	
	// 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
	//添加事务时只需要使用@Transaction(value='')
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name = "txManager1")
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource);
	}

}
