package com.meihao.kafka.producer;

/**
 * Created by monster on 2017/10/20.
 */
public class Bproducer implements Runnable {
    public static void main(String[] args) {

        Bproducer bproducer = new Bproducer();
        Thread t = new Thread(bproducer);
        t.start();
    }


    @Override
    public void run() {
        /*Properties props = new Properties();
        props.put("bootstrap.servers", "cityos3:9092,cityos4:9092,cityos5:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        int i = 1;
        while (true) {


            Producer<String, String> producer = new KafkaProducer<>(props);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


            producer.send(new ProducerRecord<>("t_status", Integer.toString(i), f.format(c.getTime())));

            i++;


            producer.close();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    }
}

