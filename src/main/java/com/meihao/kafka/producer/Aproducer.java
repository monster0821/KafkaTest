package com.meihao.kafka.producer;

/**
 * Created by monster on 2017/10/20.
 */
public class Aproducer {
    public static void main(String[] args) {
        /*Properties props = new Properties();
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<>("t_business", "topic_name", "t_business"));

        producer.close();*/
    }
}
