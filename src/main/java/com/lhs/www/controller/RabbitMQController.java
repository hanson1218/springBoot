package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.rabbitmq.rabbit.Consumer;
import com.lhs.www.rabbitmq.rabbit.Producer;
import com.lhs.www.rabbitmq.rabbit.direct.DirectConsumer1;
import com.lhs.www.rabbitmq.rabbit.direct.DirectConsumer2;
import com.lhs.www.rabbitmq.rabbit.direct.DirectProducer;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutConsumer1;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutConsumer2;
import com.lhs.www.rabbitmq.rabbit.fanout.FanoutProducer;
import com.lhs.www.rabbitmq.rabbit.topic.TopicConsumer1;
import com.lhs.www.rabbitmq.rabbit.topic.TopicConsumer2;
import com.lhs.www.rabbitmq.rabbit.topic.TopicProducer;

@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

	/**
	 * springboot 封装的rabbitMQ /*@Autowired private TopicProducer topicProducer;
	 * 
	 * @Autowired private FanoutProducer fanoutProducer;
	 * @RequestMapping("/topic") public void getTopicMessage() {
	 *                           topicProducer.send(); }
	 * @RequestMapping("/fanout") public void getFanoutMessage() {
	 *                            fanoutProducer.send(); }
	 */
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

	@Autowired
	private DirectProducer directProducer;
	@Autowired
	private DirectConsumer1 directConsumer1;
	@Autowired
	private DirectConsumer2 directConsumer2;
	
	@Autowired
	private TopicProducer topicProducer;
	@Autowired
	private TopicConsumer1 topicConsumer1;
	@Autowired
	private TopicConsumer2 topicConsumer2;

	@RequestMapping("/hello")
	public void testPublic() throws Exception {
		producer.send();
		consumer.getMessage();
		return;
	}

	@RequestMapping("/fanout")
	public void testFanout() {
		try {
			fanoutProducer.fanoutSend();
			FanoutThread1 fanoutThread = new FanoutThread1();
			new Thread(fanoutThread).start();
			FanoutThread2 fanoutThread1 = new FanoutThread2();
			new Thread(fanoutThread1).start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	@RequestMapping("/direct")
	public void testDirect()throws Exception {
		directProducer.directSend();;
		directConsumer1.getDirectMsg();
		directConsumer2.getDirectMsg();
		return;
	}
	
	@RequestMapping("/topic")
	public void testTopic()throws Exception {
		topicProducer.sendMsg();;
		
		try {
			topicConsumer2.getTopicMsg();
		} catch (Exception e) {
			topicConsumer1.getTopicMsg();
		}
		return;
	}

	class FanoutThread1 implements Runnable {
		@Override
		public void run() {
			try {
				fanoutConsumer1.fanoutConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class FanoutThread2 implements Runnable {
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
