package com.lhs.www.rabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lhs.www.common.Constant;


@Component
public class Consumer {
	
		@RabbitListener(queues = Constant.QUEUE_NAME) //监听器监听指定的Queue
	    public void receive(String msg) {
			System.out.println("test start");
	        System.out.println("receiver: " + msg);
	    }
}
