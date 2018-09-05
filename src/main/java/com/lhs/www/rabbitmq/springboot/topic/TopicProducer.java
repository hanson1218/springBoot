package com.lhs.www.rabbitmq.springboot.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.common.Constant;


/**
 * 这个类似观察者模式
 * @author pact
 *
 */
@Component
public class TopicProducer {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
        String msg1 = "I am topic.mesaage msg======";
        this.rabbitTemplate.convertAndSend(Constant.TOPIC_EXCHANGE, Constant.TOPIC_MESSAGE, msg1);
        
        /*String msg2 = "I am topic.mesaages msg########";
        this.rabbitTemplate.convertAndSend(Constant.TOPIC_EXCHANGE, Constant.TOPIC_MESSAGES, msg2);*/
    }
}
