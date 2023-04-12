package com.jwt.demo.model.openAi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIResponse {

    private String id;
    private String object;
    private Date creation;
    private String model;

    @JsonProperty("choices")
    private List<Choices> choicesList;
    @JsonProperty("usage")
    private Usage usage;
}
