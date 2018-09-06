package com.lhs.www.rabbitmq.rabbit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class ConnectionUtil {
	@Value("${spring.rabbitmq.host}")
	private String host;
	@Value("${spring.rabbitmq.username}")
	private String userName;
	@Value("${spring.rabbitmq.password}")
	private String password;
	@Value("${spring.rabbitmq.port}")
	private int port;

	public Connection getConnection() throws Exception{
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setHost(host);
		 factory.setUsername(userName);
	     factory.setPassword(password);
	     factory.setPort(port);
	     factory.setVirtualHost("/");
	     Connection connection = factory.newConnection();
		 return connection;
	}
	
}
