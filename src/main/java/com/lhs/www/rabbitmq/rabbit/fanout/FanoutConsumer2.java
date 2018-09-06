package com.lhs.www.rabbitmq.rabbit.fanout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class FanoutConsumer2 {
	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void fanoutConsumer()throws Exception{
		Connection connection = connectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		// 声明队列
        channel.queueDeclare(MQConstant.FANOUT_QUEUE_NAME_2, false, false, false, null);
        
        /*
         * 绑定队列到交换机(这个交换机的名称一定要和生产者定义的生产者交换机名称相同)
         * 参数1：队列的名称
         * 参数2：交换机的名称
         * 参数3：Routing Key
         * 
         */
        channel.queueBind(MQConstant.FANOUT_QUEUE_NAME_2,MQConstant.FANOUT_EXCHANGE_NAME, "");
        // 同一时刻服务器只会发一条消息给消费者，能者多劳模式
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成 参数1：队列名称，参数2：提交方式 true:自动提交，false：手动提交
        channel.basicConsume(MQConstant.FANOUT_QUEUE_NAME_2, false, consumer);
        
        while (true) {
        	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [消费者2] Received '" + message + "'");
            Thread.sleep(1000);
            //反馈消息的消费状态，手动提交
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			
		}
        
	}
}
