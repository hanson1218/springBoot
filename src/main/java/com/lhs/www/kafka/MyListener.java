package com.lhs.www.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;

public class MyListener {
    @KafkaListener(id = "myContainer1",//id：消费者的id，当GroupId没有被配置的时候，默认id为GroupId
            topicPartitions =//配置topic和分区：监听两个topic，分别为topic1、topic2，topic1只接收分区0，3的消息，
                    //topic2接收分区0和分区1的消息，但是分区1的消费者初始位置为5
                    { @TopicPartition(topic = "topic1", partitions = { "0", "3" }),
                            @TopicPartition(topic = "topic2", partitions = "0",
                                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "4"))
                    })

    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("topic" + record.topic());
        System.out.println("key:" + record.key());
        System.out.println("value:"+record.value());
    }

    @KafkaListener(id = "${kafka.producer.group-id}",topics = {"foo","bar"})
    public void listen2(ConsumerRecord<?, ?> record){
        System.out.println("topic：" + record.topic());
        System.out.println("key:" + record.key());
        System.out.println("value:"+record.value());

    }

}
