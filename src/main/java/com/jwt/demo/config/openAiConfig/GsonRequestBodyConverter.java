package com.jwt.demo.config.openAiConfig;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private final Gson gson;
    private final Type type;

    public GsonRequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String json = gson.toJson(value, type);
        return RequestBody.create(MediaType.parse("application/json"), json);
    }
}
