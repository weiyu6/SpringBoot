package com.wytest.springboot.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/26
 */
@RestController
public class NewProducer {

    private static final Log logger = LogFactory.getLog(NewProducer.class);
    @RequestMapping("newProducer")
    public void newProducer(String msg){

        logger.debug("开始了"+msg);
        if(msg==null){
            msg = "hello world!";
        }
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("myTopic", msg);
        Future<RecordMetadata> send = producer.send(record);
        producer.close();

        logger.debug("消息发送完毕！");
    }
}
