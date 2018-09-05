package com.lhs.www.rabbitmq.springboot.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhs.www.common.Constant;

/**
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
 * @author pact
 *
 */
@Component
public class FanoutProducer {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
        String msg1 = "I am fanout.mesaage A======";
        this.rabbitTemplate.convertAndSend(Constant.FANOUT_EXCHANGE, "1", msg1);
        
        String msg2 = "I am fanout.mesaage B======";
        this.rabbitTemplate.convertAndSend(Constant.FANOUT_EXCHANGE, "w", msg2);
        
        
    }
	
}
