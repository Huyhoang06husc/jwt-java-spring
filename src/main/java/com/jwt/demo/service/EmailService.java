package com.jwt.demo.service;

import com.jwt.demo.model.EmailDetail;

public interface EmailService {

    String sendSimpleMail(EmailDetail emailDetail);

    String sendMailWithAttachment(EmailDetail emailDetail);
}
