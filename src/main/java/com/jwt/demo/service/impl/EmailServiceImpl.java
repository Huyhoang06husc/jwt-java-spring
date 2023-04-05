package com.jwt.demo.service.impl;

import com.jwt.demo.exception.SendMailException;
import com.jwt.demo.model.EmailDetail;
import com.jwt.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetail emailDetail) {
        try {
            SimpleMailMessage mailMessage =
                    new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetail.getRecipient());
            mailMessage.setText(emailDetail.getMsgBody());
            mailMessage.setSubject(emailDetail.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            System.out.println("=========================");
            System.out.println(e);
            return "fail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetail emailDetail) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(emailDetail.getMsgBody());
            mimeMessageHelper.setSubject(mimeMessage.getSubject());

            FileSystemResource file
                    = new FileSystemResource(
                            new File(emailDetail.getAttachment())
            );
            mimeMessageHelper.addAttachment(file.getFilename(),file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (Exception e) {
            return "Error while sending mail!!!";
        }
    }
}
