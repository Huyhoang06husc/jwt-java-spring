package com.jwt.demo.config.openAiConfig;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MyCustomConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static MyCustomConverterFactory create() {
        return create(new Gson());
    }

    public static MyCustomConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyCustomConverterFactory(gson);
    }

    private MyCustomConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new Converter<Object, RequestBody>() {
            @Override
            public RequestBody convert(Object value) throws IOException {
                String json = gson.toJson(value);
                return RequestBody.create(MediaType.parse("application/json"), json);
            }
        };
    }
}
