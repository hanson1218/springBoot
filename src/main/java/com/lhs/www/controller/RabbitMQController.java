package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.rabbitmq.rabbit.Consumer;
import com.lhs.www.rabbitmq.rabbit.Producer;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutConsumer1;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutConsumer2;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutProducer;

@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

	/**springboot 封装的rabbitMQ
	/*@Autowired
	private TopicProducer topicProducer;
	
	@Autowired
	private FanoutProducer fanoutProducer;
	
	
	@RequestMapping("/topic")
	public void getTopicMessage() {
		topicProducer.send();
	}
	
	@RequestMapping("/fanout")
	public void getFanoutMessage() {
		fanoutProducer.send();
	}*/
	@Autowired
	private Producer producer;
	
	@Autowired
	private Consumer consumer;
	
	@Autowired
	private FanoutProducer fanoutProducer;
	
	@Autowired
	private FanoutConsumer1 fanoutConsumer1;
	@Autowired
	private FanoutConsumer2 fanoutConsumer2;
	
	@RequestMapping("/hello")
	public void testPublic()throws Exception{
		producer.send();
		consumer.getMessage();
		return;
	}
	
	@RequestMapping("/fanout")
	public void testFanout(){
		try {
			fanoutProducer.fanoutSend();
			FanoutThread1 fanoutThread=new FanoutThread1();
			new Thread(fanoutThread).start();
			FanoutThread2 fanoutThread1=new FanoutThread2();
			new Thread(fanoutThread1).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	class FanoutThread1 implements Runnable{
		@Override
		public void run() {
			try {
				fanoutConsumer1.fanoutConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class FanoutThread2 implements Runnable{
		@Override
		public void run() {
			try {
				fanoutConsumer2.fanoutConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
