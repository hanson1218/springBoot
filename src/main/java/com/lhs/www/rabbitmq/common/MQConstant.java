package com.lhs.www.rabbitmq.common;

public class MQConstant {
	public static final String QUEUE_NAME = "test_queue";
	
	//****fanout广播模式***//
	//交换机
	public final static String FANOUT_EXCHANGE_NAME = "exchange_fanout";
	//队列名称
	public final static String FANOUT_QUEUE_NAME = "QUEUE_fanout1";
	public final static String FANOUT_QUEUE_NAME_2 = "QUEUE_fanout2";
	//****fanout广播模式***//
	
	//****direct 完全匹配模式***//
	//交换机
	public final static String DIRECT_EXCHANGE_NAME = "exchange_direct";
	public final static String DIRECT_QUEUE_NAME = "QUEUE_direct1";
	public final static String DIRECT_QUEUE_NAME_2 = "QUEUE_direct2";
	//****direct 完全匹配模式***//
	
	//****topic 通配符模式****//
	public final static String TOPIC_EXCHANGE_NAME = "exchange_topic";
	public final static String TOPIC_QUEUE_NAME = "QUEUE_topic1";
	public final static String TOPIC_QUEUE_NAME_2 = "QUEUE_topic2";
	//****topic 通配符模式****//
}
