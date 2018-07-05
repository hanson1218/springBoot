package com.lhs.www.initConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lhs.www.common.Constant;


@Configuration
public class RabbitMQConfig {
	
    
    @Bean(name="taskQueue")
    public Queue queue() {
        return new Queue(Constant.QUEUE_NAME);
    }
    
    //topic Exchange的队列
    @Bean
    public Queue queueMessage() {
        return new Queue(Constant.TOPIC_MESSAGE);
    }

    @Bean
    public Queue queueMessages() {
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
        return new Queue(Constant.TOPIC_MESSAGES);
    }
    
    /**
     * 这个位置的topic name和生产者push的exchange name要一致
     * @return
     */
    @Bean
    TopicExchange exchange() {
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	System.out.println("test1");
    	
    	System.out.println("test1");
        return new TopicExchange(Constant.TOPIC_EXCHANGE);
    }
    
    
    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(Constant.TOPIC_MESSAGE);
    }
    
    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
    
    
    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue(Constant.FANOUT_A);
    }

    /**
     * 这个位置Bean name ，在bind中会以名字注入
     * @return
     */
    @Bean
    public Queue BMessage() {
        return new Queue(Constant.FANOUT_B);
    }
    
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("reall");
    }
    @Bean
    FanoutExchange fanoutExchange1() {
        return new FanoutExchange("reall111");
    }
    
    /**
     * 这个位置参数名称要和定义的Queue名称相同，fanoutExchange要和定义的exchange相同
     * @param AMessage
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
    	System.out.println("**************"+AMessage.getName());
    	System.out.println(fanoutExchange.getName());
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

}
