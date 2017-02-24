package com.spring.exercize.service.impl;

import org.springframework.stereotype.Component;

/**
 * Created by zhaixiaotong on 2016-7-18.
 */
@Component
public class ActionTest {

    public  String test(){

        System.out.println("action done");
        return "test";
    }
}
