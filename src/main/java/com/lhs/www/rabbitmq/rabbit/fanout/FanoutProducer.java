package com.lhs.www.rabbitmq.rabbit.fanout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 订阅者模式，会忽略routing key的存在，直接将message广播到所有的Queue中
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上,
 * 类似观察者模式
 * @author LiHuaSheng
 *
 */
@Component
public class FanoutProducer {

	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void fanoutSend() throws Exception{
		Connection connection = connectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		/*
         * 声明exchange(交换机)
         * 参数1：交换机名称
         * 参数2：交换机类型
         * 参数3：交换机持久性，如果为true则服务器重启时不会丢失
         * 参数4：交换机在不被使用时是否删除
         * 参数5：交换机的其他属性
         */
        channel.exchangeDeclare(MQConstant.FANOUT_EXCHANGE_NAME, "fanout",true,true,null);
        for(int i=1;i<=50;i++){
        	String message= "订阅模式消息"+i;
            channel.basicPublish(MQConstant.FANOUT_EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [生产者] Sent '" + message + "'");
        }
        
        channel.close();
        connection.close();
	}
}
