package com.lhs.www.rabbitmq;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lhs.www.entity.User;


@Component
public class Producer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
//	@Resource(name="taskQueue")
//	一起使用表示按名称查找
	@Autowired
	@Qualifier("taskQueue")
	private Queue queue;
	
	@PostConstruct
    public void test() {
        this.send();
    }
	
	public void send () {
        
        // 发送对象类型的消息
        User event = new User(); //实现Serializable接口
        event.setId(1101);
        event.setName("printscreen event");
        event.setAddress("深圳");
        event.setPhone("123");
         
        System.out.println(queue.getName());
		amqpTemplate.convertAndSend(queue.getName(), event.toString()); // 队列名称，消息
    }
}
