package com.jwt.demo.service;

import com.jwt.demo.model.openAi.OpenAIRequest;
import com.jwt.demo.model.openAi.OpenAIResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OpenAIInterface {

    @Headers({
            "Content-Type: application/json"
    })
    @POST("completions")
    Call<OpenAIResponse> sendMessage(@Header("Authorization") String authHeader, @Body OpenAIRequest request);

}
