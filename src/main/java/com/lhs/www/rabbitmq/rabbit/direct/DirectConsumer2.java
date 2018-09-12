package com.lhs.www.rabbitmq.rabbit.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class DirectConsumer2 {
	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void getDirectMsg() throws Exception{
		Connection connection = connectionUtil.getConnection();
		Channel channel = connection.createChannel();
		// 声明队列
        channel.queueDeclare(MQConstant.DIRECT_QUEUE_NAME_2, false, false, false, null);
        
        /*
         * 绑定队列到交换机
         * 参数1：队列的名称
         * 参数2：交换机的名称
         * 参数3：routingKey
         */
        channel.queueBind(MQConstant.DIRECT_QUEUE_NAME_2,MQConstant.DIRECT_EXCHANGE_NAME, "B");
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(MQConstant.DIRECT_QUEUE_NAME_2, false, consumer);
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        String message = new String(delivery.getBody());
        System.out.println(" [消费者2] Received '" + message + "'");

        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	}
}
