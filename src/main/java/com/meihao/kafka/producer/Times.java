package com.meihao.kafka.producer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by monster on 2017/10/20.
 */
public class Times implements Runnable {
    public static void main(String[] args) {
        Times times = new Times();
        Thread t = new Thread(times);
        t.start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println(f.format(c.getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
