package com.jiu;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void sendMsg() {

        for (int i = 0; i < 10; i++) {
            String number = "139";
            Random random = new Random();
            Map map = new HashMap<>();
            map.put("nickName","我是mybatis"+i); //昵称
            map.put("phone",number);  //手机号
            map.put("password","000000");  //密码
            String json = JSON.toJSONString(map);
            Message msg = new Message("user-topic", "white", json.getBytes());
            try {
                SendResult result = defaultMQProducer.send(msg);
                System.out.println("消息id:" + result.getMsgId() + ":" + "," + "发送状态:" + result.getSendStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
