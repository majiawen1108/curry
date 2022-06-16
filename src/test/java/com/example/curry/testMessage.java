package com.example.curry;

import com.example.curry.CurryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author jw.ma
 * @title: com.example.curry.testMessage
 * @description: TODO
 * @date 2022/6/8 17:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurryApplication.class)
public class testMessage {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${active.name}")
    private String name;

    @Test
    public void sendMessage(){
        jmsMessagingTemplate.convertAndSend(name,"这是一个active客户发送的信息");
    }

}
