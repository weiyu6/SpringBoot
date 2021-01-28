package com.wytest.springboot.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/26
 */
@RestController
public class ConsumerTest {

    private static final Log logger = LogFactory.getLog(ConsumerTest.class);

    @RequestMapping("consumerTest")
    public void consumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-consumer-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        List<String> topicList = new ArrayList<String>();
        topicList.add("myTopic");
        consumer.subscribe(topicList);
        int i = 0;
        while (true) {
            logger.debug("nihao" + i);
            i++;
            ConsumerRecords<String, String> records = consumer.poll(5000);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                logger.debug("kafka的值：{" + value + "}");
            }
        }
    }
}
