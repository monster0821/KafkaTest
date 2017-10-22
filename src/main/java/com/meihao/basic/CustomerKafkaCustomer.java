package com.meihao.basic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by monster on 2017/10/18.
 */
public class CustomerKafkaCustomer {

    /*public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("group.id", "test");//消费者组
        props.put("enable.auto.commit", "true");//设置自动提交
        props.put("auto.commit.interval.ms", "1000");//偏移量由此控制自动提交的频率
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic"));//topic
        *//*String topic = "my-topic";
        TopicPartition partition0 = new TopicPartition(topic,0);
        TopicPartition partition1 = new TopicPartition(topic,1);
        consumer.assign(Arrays.asList(partition0,partition1));*//*
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }


    }*/


    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable(Integer.toString(i)));
        }
    }
}

class MyRunnable implements Runnable {

    private Properties props = new Properties();
    private KafkaConsumer<String, String> consumer;
    private String id;

    public MyRunnable(String id) {
        this.id = id;
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("group.id", this.id);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic"));
    }


    public void run() {
        System.out.println(Thread.currentThread().getName() + "---启动!");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("%s,offset = %d, key = %s, value = %s%n", Thread.currentThread().getName(), record.offset(), record.key(), record.value());
            }
        }

    }
}