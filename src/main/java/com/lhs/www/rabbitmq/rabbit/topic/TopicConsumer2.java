package com.lhs.www.rabbitmq.rabbit.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
@Component
public class TopicConsumer2 {
	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void getTopicMsg()throws Exception{
		// 获取到连接以及mq通道
        Connection connection = connectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //申明队列
        channel.queueDeclare(MQConstant.TOPIC_QUEUE_NAME,false,false,false,null);
        channel.queueBind(MQConstant.TOPIC_QUEUE_NAME, MQConstant.TOPIC_EXCHANGE_NAME, "topic.*");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回。完成参数1：队列名称，参数2：提交方式 true:自动提交，false：手动提交
        channel.basicConsume(MQConstant.TOPIC_QUEUE_NAME, false, consumer);
        int a = 1/0;
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        String message = new String(delivery.getBody());
        System.out.println(" [财务系统2] Received '" + message + "'");
        Thread.sleep(10);
        
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	}
}
