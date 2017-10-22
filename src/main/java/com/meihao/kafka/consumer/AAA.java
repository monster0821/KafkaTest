package com.meihao.kafka.consumer;

import java.io.InputStream;

/**
 * Created by monster on 2017/10/20.
 */
public class AAA {
    public static void main(String[] args) {

    }

    public void run() {
        InputStream in = getClass().getResourceAsStream("config.properties");
        String s = in.toString();
        System.out.println(s);
    }
}
