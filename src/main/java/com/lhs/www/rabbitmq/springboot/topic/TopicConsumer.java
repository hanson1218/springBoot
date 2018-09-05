package com.lhs.www.rabbitmq.springboot.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lhs.www.common.Constant;

@Component
public class TopicConsumer {
	
	@RabbitListener(queues = Constant.TOPIC_MESSAGE)
	public void receiver1(String msg) {
        System.out.println("topicMessageReceiver1  : " +msg);
    }
	
//	@RabbitListener(queues = Constant.TOPIC_MESSAGES)
	public void receiver2(String msg) {
        System.out.println("topicMessageReceiver2  : " +msg);
    }
}
