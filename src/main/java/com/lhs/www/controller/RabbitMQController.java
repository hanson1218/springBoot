package com.lhs.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.www.rabbitmq.springboot.fanout.FanoutProducer;
import com.lhs.www.rabbitmq.springboot.topic.TopicProducer;

@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

	@Autowired
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
	}
}
