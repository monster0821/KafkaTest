package com.meihao.basic;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by monster on 2017/10/18.
 */
public class KafkasProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("acks", "all");//判断请求是否为完整的条件,"all"将会阻塞信息,性能低但是可靠
        props.put("retries", 0);//如果请求失败,生产者会自动重试,指定0次,果如启用重试则有可能重复消息
        props.put("batch.size", 16384);//缓存的大小
        props.put("linger.ms", 1);//默认缓存可立即发送,linger.ms>0时,可减少请求的数量
        props.put("buffer.memory", 33554432);//控制生产者可用的缓存总量
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");//将用户提供的key对象ProducerRecord转换成字节
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");//将用户提供的value对象ProducerRecord转换成字节

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));//send方法是异步的,添加消息到缓存区等待发送,并立即返回生产者将单个的消息批量在一起发送来提高效率

        producer.close();
    }
}
