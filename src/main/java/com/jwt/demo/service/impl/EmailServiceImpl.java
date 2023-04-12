package com.jwt.demo.service.impl;

import com.jwt.demo.model.EmailDetail;
import com.jwt.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.port}")
    private int smtpPort;

    @Value("${spring.mail.username}")
    private String smtpUsername;

    @Value("${spring.mail.password}")
    private String smtpPassword;

    @Override
    public void sendSimpleMail(EmailDetail emailDetail) throws MessagingException {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setPort(smtpPort);
        sender.setHost(smtpHost);
        sender.setUsername(smtpUsername);
        sender.setPassword(smtpPassword);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String[] recipients = emailDetail.getRecipient();

        for (int i = 0; i < emailDetail.getRecipient().length; i++) {
            helper.setTo(recipients[i]);
            helper.setSubject("Hi " + recipients[i] + " " + emailDetail.getSubject());
            helper.setText("Hi " + recipients[i] + " , \n" + emailDetail.getMsgBody());
            sender.send(message);
        }

    }

    @Override
    public String sendMailWithAttachment(EmailDetail emailDetail) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(emailDetail.getMsgBody());
            mimeMessageHelper.setSubject(mimeMessage.getSubject());

            FileSystemResource file
                    = new FileSystemResource(
                    new File(emailDetail.getAttachment())
            );
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (Exception e) {
            return "Error while sending mail!!!";
        }
    }
}
