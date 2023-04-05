package com.jwt.demo.controller.mailController;

import com.jwt.demo.model.EmailDetail;
import com.jwt.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody EmailDetail emailDetail) {
        try {
            String status =
                    emailService.sendSimpleMail(emailDetail);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PostMapping(value = "/sendMailAttachment")
    public ResponseEntity<?> sendMailAttachment(@RequestBody EmailDetail emailDetail) {
        try {
            String status =
                    emailService.sendMailWithAttachment(emailDetail);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
