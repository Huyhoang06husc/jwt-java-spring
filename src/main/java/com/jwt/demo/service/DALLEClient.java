package com.jwt.demo.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DALLEClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String DALLE_API_URL = "https://api.openai.com/v1/images/generations";

    private final OkHttpClient client = new OkHttpClient();

    public String generateImage(String prompt) throws IOException {
        String json = "{\n" +
                "  \"model\": \"image-alpha-001\",\n" +
                "  \"prompt\": \"" + prompt + "\",\n" +
                "  \"num_images\": 1,\n" +
                "  \"size\": \"512x512\",\n" +
                "  \"response_format\": \"url\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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")
                .url(DALLE_API_URL)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
