package com.jiu;

import com.jiu.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaiTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("hbwhlukai@163.com","245220452@qq.com","测试标题","测试内容");
    }

    @Test
    public void sendAttachmentsMail(){
        mailService.sendAttachmentsMail("hbwhlukai@163.com","245220452@qq.com","测试标题","测试内容","d:\\workdemo\\jiujiu-master\\pom.xml");
    }
}
