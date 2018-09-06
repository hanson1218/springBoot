package com.lhs.www.rabbitmq.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component
public class Producer {
	
	@Autowired
	private ConnectionUtil connectionUtil;
	public void send() throws Exception{
        // 获取到连接
		Connection connection = connectionUtil.getConnection();
		//从连接中创建MQ通道
		Channel channel = connection.createChannel();
		// 声明队列
		/**
		 * 	队列队列的名称
		           如果我们声明一个持久队列（队列将在服务器重启后仍然存在），则为true
       		如果我们声明一个独占队列（仅限于此连接），则为true
     		autoDelete如果我们声明一个自动删除队列，则为true（服务器将在不再使用时将其删除）
      		参数队列的其他属性（构造参数）
		 */
        channel.queueDeclare(MQConstant.QUEUE_NAME, false, false, false, null);
        
        /*
         * 向server发布一条消息
         * 参数1：exchange名字，若为空则使用默认的exchange
         * 参数2：routing key
         * 参数3：其他的属性
         * 参数4：消息体
         * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
         * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
         */
        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", MQConstant.QUEUE_NAME, null, message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");
        
        //关闭通道和连接
        channel.close();
        connection.close();
	}
}
