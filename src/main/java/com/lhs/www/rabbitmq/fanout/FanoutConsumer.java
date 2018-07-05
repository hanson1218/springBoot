package com.lhs.www.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lhs.www.common.Constant;

@Component
public class FanoutConsumer {

	@RabbitListener(queues = Constant.FANOUT_A)
	public void receiver1(String msg) {
        System.out.println("fanoutMessageReceiver  : " +msg);
    }
	
	/*@RabbitListener(queues = Constant.FANOUT_B)
	public void receiver2(String msg) {
        System.out.println("fanoutMessageReceiver TOP  : " +msg);
    }*/
}
