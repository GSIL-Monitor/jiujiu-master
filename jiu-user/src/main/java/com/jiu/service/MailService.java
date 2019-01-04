package com.jiu.service;

/**
 * Created by lukai on 2019/1/4.
 */
public interface MailService {

    public void sendSimpleMail(String from ,String to, String subject, String content);

    public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath);
}
