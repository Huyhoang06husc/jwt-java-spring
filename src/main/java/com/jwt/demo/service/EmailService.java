package com.jwt.demo.service;

import com.jwt.demo.model.EmailDetail;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

public interface EmailService {

    void sendSimpleMail(EmailDetail emailDetail) throws MessagingException;

    String sendMailWithAttachment(EmailDetail emailDetail);
}
