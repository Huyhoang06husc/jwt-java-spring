package com.jwt.demo.config.openAiConfig;

import com.jwt.demo.service.OpenAIInterface;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Configuration
public class RetrofitConfig {

    @Value("${openai.api.base-url}")
    private String baseUrl;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Bean
    public Retrofit retrofit() {
        Moshi moshi = new Moshi.Builder().build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactoryAdapter.create(moshi))
                .client(okHttpClient())
                .build();
    }

    @Bean
    public OpenAIInterface openAIInterface() {
        return retrofit().create(OpenAIInterface.class);
    }
}
