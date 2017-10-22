package com.meihao.kafka.consumer;

/**
 * Created by monster on 2017/10/20.
 */
public class Aconsumer {
    public static void main(String[] args) {

       /* Properties props = new Properties();
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("group.id", "aconsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("t_business"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
                String s = record.value();
                System.out.println(s);
                //System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }

        }*/
    }
}
