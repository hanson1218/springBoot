package com.lhs.www.rabbitmq.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class Consumer {

	@Autowired
	private ConnectionUtil connectionUtil;

	public void getMessage() throws Exception {
		// 获取到连接
		Connection connection = connectionUtil.getConnection();
		// 从连接中创建MQ通道
		Channel channel = connection.createChannel();
		// 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
		// channel.queueDeclare(MQConstant.QUEUE_NAME, false, false, false,
		// null);
		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		/*
		 * 监听队列 参数1:队列名称 参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。
		 * 可以通过channel.basicAck手动回复ack 参数3：消费者
		 */
		channel.basicConsume(MQConstant.QUEUE_NAME, true, consumer);
		// 从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待 
		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		String message = new String(delivery.getBody());
		System.out.println(" [消费者] Received '" + message + "'");

	}
}
