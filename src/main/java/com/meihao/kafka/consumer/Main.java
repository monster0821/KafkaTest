package com.meihao.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by monster on 2017/10/20.
 */
public class Main {

    static InputStream in;
    static Properties props;

    static {
        try {
            props = new Properties();
            in = Main.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        Apro apro = new Main.Apro();
        Thread t1 = new Thread(apro);
        t1.start();

        /*Acon acon = new Main.Acon();
        Thread t2 = new Thread(acon);
        t2.start();*/

        Bpro bpro = new Main.Bpro();
        Thread t3 = new Thread(bpro);
        t3.start();

       /* Bcon bcon = new Main.Bcon();
        Thread t4 = new Thread(bcon);
        t4.start();*/

    }


    static class Apro implements Runnable {
        //业务business
        //状态status
        @Override
        public void run() {
            String b_topic_name = props.getProperty("b_topic_name");
            String S_topic_name = props.getProperty("S_topic_name");
            props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<>(props);
            producer.send(new ProducerRecord<>(S_topic_name, "topic_name", b_topic_name));
            producer.close();
        }
    }


    static class Bpro implements Runnable {

        @Override
        public void run() {

            String S_topic_name = props.getProperty("S_topic_name");
            props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            int i = 1;
            Producer<String, String> producer = new KafkaProducer<>(props);
            while (true) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                producer.send(new ProducerRecord<>(S_topic_name, Integer.toString(i), f.format(c.getTime())));
                i++;

                try {


                    Thread.sleep(1000);


                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                producer.close();
            }


        }

    }

    static abstract class Acon implements Callable<String> {


        public static void main(String[] args) {
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String b_topic_name = props.getProperty("b_topic_name");
                    //String S_topic_name = props.getProperty("S_topic_name");
                    props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
                    props.put("group.id", "aconsumer");
                    props.put("enable.auto.commit", "true");
                    props.put("auto.commit.interval.ms", "1000");
                    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

                    consumer.subscribe(Arrays.asList(b_topic_name));
                    String s = null;
                    while (true) {
                        ConsumerRecords<String, String> records = consumer.poll(10);
                        for (ConsumerRecord<String, String> record : records) {
                            s = record.value();
                        }
                        System.out.println("**");
                        return s;
                    }
                }
            };
            FutureTask<String> future = new FutureTask<String>(callable);
            new Thread(future).start();
            try {
                String b_topic_name = props.getProperty("b_topic_name");
                String S_topic_name = props.getProperty("S_topic_name");
                props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
                props.put("group.id", future.get());
                props.put("enable.auto.commit", "true");
                props.put("auto.commit.interval.ms", "1000");
                props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList(b_topic_name));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(10);
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}


      /*  @Override
        public String call() throws Exception {


            String b_topic_name = props.getProperty("b_topic_name");
            //String S_topic_name = props.getProperty("S_topic_name");
            props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
            props.put("group.id", "aconsumer");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

            consumer.subscribe(Arrays.asList(b_topic_name));
            String s = null;
            while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {

                s = record.value();
            }


        }



            return s;
            FutureTask<String> future = new FutureTask<String>();
            new Thread(future).start();
        }*/




    /*static class Bcon implements Runnable {

        @Override
        public void run() {
            *//*String b_topic_name = props.getProperty("b_topic_name");
            String S_topic_name = props.getProperty("S_topic_name");
            props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
            props.put("group.id", s);
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(b_topic_name));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }*//*
        }
    }
}
*/
