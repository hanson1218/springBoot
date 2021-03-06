package com.lhs.www.initConfig;

import java.beans.PropertyVetoException;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	/**
	 * 另一个种配置方式：@ConfigurationProperties(prefix="jdbc")
	 * @return
	 * @throws PropertyVetoException
	 */
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
	
	/*@Bean(name="dataSource")
	public BasicDataSource  createDataSource(){
		BasicDataSource  dataSource = new BasicDataSource ();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		System.out.println("test"+dataSource.getMaxIdle());
		return dataSource;
	}*/
}
