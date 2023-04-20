package com.jwt.demo.controller.dalleController;

import com.jwt.demo.service.DALLEClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/dalle")
public class DallEController {

    @Autowired
    private DALLEClient dalleClient;

    @PostMapping(value = "/generate-image", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateImage(@RequestParam String prompt) {
        try {
            String imageBytes = dalleClient.generateImage(prompt);
            return ResponseEntity.ok().body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
