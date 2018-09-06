package com.lhs.www.rabbitmq.rabbit.fanout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 1.声明队列
 * 2.队列绑定队列到交换机，通过routerKey
 * 3.定义队列的消费者
 * 4.监听队列，手动返回
 * 5.消费消息
 * 6.返回消费的状态
 * @author LiHuaSheng
 *
 */
@Component
public class FanoutConsumer1 {
	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void fanoutConsumer()throws Exception{
		Connection connection = connectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		// 声明队列
        channel.queueDeclare(MQConstant.FANOUT_QUEUE_NAME, false, false, false, null);
        
        /*
         * 绑定队列到交换机(这个交换机的名称一定要和生产者定义的的生产者交换机名称相同)
         * 参数1：队列的名称
         * 参数2：交换机的名称
         * 参数3：Routing Key
         * 
         */
        channel.queueBind(MQConstant.FANOUT_QUEUE_NAME,MQConstant.FANOUT_EXCHANGE_NAME,  "");
        // 同一时刻服务器只会发一条消息给消费者 能者多劳模式，
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回。完成参数1：队列名称，参数2：提交方式 true:自动提交，false：手动提交
        channel.basicConsume(MQConstant.FANOUT_QUEUE_NAME, false, consumer);
        
        while (true) {
        	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [消费者1] Received '" + message + "'");
            Thread.sleep(10);
            //反馈消息的消费状态，手动提交
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
        /**
         * 消息的确认
		在以上的代码中,已经给出了注释,如何使用自动确认和手动确认,消费者从队列中获取消息，服务端如何知道消息已经被消费呢？
		模式1：自动确认
		只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费。
		模式2：手动确认
		消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
		如果选用自动确认,在消费者拿走消息执行过程中出现宕机时,消息可能就会丢失！！
         */
        
	}
}
