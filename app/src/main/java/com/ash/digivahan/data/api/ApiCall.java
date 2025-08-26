package com.ash.digivahan.data.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCall {

    private static OkHttpClient client;
    private static final long TIMEOUT = 60; // seconds

    public static synchronized OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return client;
    }

    // GET
    public static String GET(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = getClient().newCall(request).execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }

    // POST
    public static String POST(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = getClient().newCall(request).execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }
}

