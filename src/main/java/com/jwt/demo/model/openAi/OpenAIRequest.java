package com.jwt.demo.model.openAi;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIRequest {
    private String model;
    private String prompt;
    private Integer temperature;
    private Integer max_tokens;
    private Integer presence_penalty;
    private Integer frequency_penalty;
    private Integer top_p;
    private List<String> stop;
}
