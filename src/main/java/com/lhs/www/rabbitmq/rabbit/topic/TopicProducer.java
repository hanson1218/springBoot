package com.lhs.www.rabbitmq.rabbit.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 订阅发布
 * 生产着是具体的router key, 消费者是定义# 或者 .
 * @author LiHuaSheng
 * 
 */
@Component
public class TopicProducer {
	@Autowired
	private ConnectionUtil connectionUtil;

	public void sendMsg() throws Exception {
		// 获取到连接
		Connection connection = connectionUtil.getConnection();
		// 从连接中创建MQ通道
		Channel channel = connection.createChannel();
		// 声明exchange
		channel.exchangeDeclare(MQConstant.TOPIC_EXCHANGE_NAME, "topic");

		// 消息内容
		String message = "topic message";
		channel.basicPublish(MQConstant.TOPIC_EXCHANGE_NAME,"topic.test", null,message.getBytes());
		System.out.println(" [生产者] Sent '" + message + "'");

		// 关闭通道和连接
		channel.close();
		connection.close();
	}
}
