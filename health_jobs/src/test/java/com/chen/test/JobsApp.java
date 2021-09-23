package com.chen.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobsApp {

    @Test
    public void JobsAppTest(){
        new ClassPathXmlApplicationContext("classpath:applicationContext-jobs.xml");
    }
}
