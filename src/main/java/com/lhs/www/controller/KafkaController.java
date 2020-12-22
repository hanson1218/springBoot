package com.lhs.www.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/{topic}/send",method = RequestMethod.GET)
    public void sendMeessageTotopic1(@PathVariable String topic, @RequestParam(value = "partition",defaultValue = "0") int partition) {
        log.info("start send message to {}",topic);
        kafkaTemplate.send(topic,"你","好");
    }


    @RequestMapping("syncSendMessage")
    public String syncSendMessage() {
//        for (int i = 0; i < 100; i++) {
//            try {
////                template.send("kafka-boot", "0", "foo" + i).get();
//            } catch (InterruptedException e) {
////                logger.error("sync send message fail [{}]", e.getMessage());
//                e.printStackTrace();
//            } catch (ExecutionException e) {
////                logger.error("sync send message fail [{}]", e.getMessage());
//                e.printStackTrace();
//            }
//        }
        return "success";
    }

}
