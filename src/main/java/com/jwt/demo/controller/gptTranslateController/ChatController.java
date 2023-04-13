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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {


    @Autowired
    private OpenAIChatGPTAPIService openAIChatGPTAPIService;


    @PostMapping("/gpt")
    public ResponseEntity<String> grammar(@RequestParam String text) {
        OpenAIResponse response = openAIChatGPTAPIService.sendMessage(text);
        if (null == response) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<String> responseList = response.getChoicesList().stream().map(Choices::getText).collect(Collectors.toList());

        String finalResponse = responseList.get(0);
        finalResponse = finalResponse.replace("\n", System.getProperty("line.separator"));
        return new ResponseEntity<>(finalResponse
                , HttpStatus.OK);

    }
}
