package com.lhs.www.rabbitmq.rabbit.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.rabbitmq.common.MQConstant;
import com.lhs.www.rabbitmq.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 完全匹配的router key才获取消息
 * @author LiHuaSheng
 *
 */
@Component
public class DirectProducer {
	@Autowired
	private ConnectionUtil connectionUtil;
	
	public void directSend() throws Exception{
		Connection connection = connectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		// 声明exchange
        channel.exchangeDeclare(MQConstant.DIRECT_EXCHANGE_NAME, "direct");
        
        // 消息内容
        String message = "这是消息B";
        //参数1：交换机名称，2：router key 4:内容
        channel.basicPublish(MQConstant.DIRECT_EXCHANGE_NAME, "B", null, message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");
 
        channel.close();
        connection.close();
	}
}
