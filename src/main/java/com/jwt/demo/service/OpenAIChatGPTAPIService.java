package com.jwt.demo.service;

import com.jwt.demo.constants.openAi.OpenAIConstants;
import com.jwt.demo.exception.SendChatGptException;
import com.jwt.demo.model.openAi.OpenAIRequest;
import com.jwt.demo.model.openAi.OpenAIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIChatGPTAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String token;

    public OpenAIResponse sendMessage(String text){
        HttpHeaders headers = new HttpHeaders();
        headers.add(OpenAIConstants.AUTHORIZATION, "Bearer " + token);

        OpenAIRequest ai = new OpenAIRequest();
        ai.setModel(OpenAIConstants.MODEL);
        ai.setPrompt(text);
        ai.setFrequency_penalty(0);
        ai.setPresence_penalty(0);
        HttpEntity entity = new HttpEntity(ai, headers);

        try {
            ResponseEntity<OpenAIResponse> data = restTemplate.exchange("https://api.openai.com/v1/completions", HttpMethod.POST, entity, OpenAIResponse.class);
            return data.getBody();
        } catch (Exception e) {
            throw new SendChatGptException("Currently, the chatGPT server is having problems. Please try again later.");
        }
    }
}
