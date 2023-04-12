package com.jwt.demo.controller.gptTranslateController;

import com.jwt.demo.model.openAi.Choices;
import com.jwt.demo.model.openAi.OpenAIResponse;
import com.jwt.demo.service.OpenAIChatGPTAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {


    @Autowired
    private OpenAIChatGPTAPIService openAIChatGPTAPIService;


    @PostMapping
    public ResponseEntity<?> grammar(@RequestParam String text) throws IOException {
        OpenAIResponse response = openAIChatGPTAPIService.sendMessage(text);
        if (null == response){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.getChoicesList().stream().map(Choices::getText).collect(Collectors.toList())
                , HttpStatus.OK);
    }
}
