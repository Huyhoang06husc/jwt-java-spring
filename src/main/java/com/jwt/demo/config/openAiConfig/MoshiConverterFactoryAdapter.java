package com.jwt.demo.config.openAiConfig;

import com.squareup.moshi.Moshi;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public final class MoshiConverterFactoryAdapter extends Factory {
    private final Moshi moshi;

    private MoshiConverterFactoryAdapter(Moshi moshi) {
        this.moshi = moshi;
    }

    public static MoshiConverterFactoryAdapter create() {
        return create(new Moshi.Builder().build());
    }

    public static MoshiConverterFactoryAdapter create(Moshi moshi) {
        return new MoshiConverterFactoryAdapter(moshi);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            retrofit2.Retrofit retrofit) {
        return (Converter<ResponseBody, ?>) moshi.adapter(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
                                                          retrofit2.Retrofit retrofit) {
        return new MoshiRequestBodyConverter<>(moshi.adapter(type));
    }

    static final class MoshiRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final com.squareup.moshi.JsonAdapter<T> adapter;

        MoshiRequestBodyConverter(com.squareup.moshi.JsonAdapter<T> adapter) {
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return RequestBody.create(okhttp3.MediaType.parse("application/json"), adapter.toJson(value));
        }
    }
}
