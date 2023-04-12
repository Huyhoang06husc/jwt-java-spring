package com.jwt.demo.model.openAi;

import lombok.Data;

@Data
public class Usage {

    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
