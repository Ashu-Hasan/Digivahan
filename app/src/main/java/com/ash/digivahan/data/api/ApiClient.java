package com.ash.digivahan.data.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static String androidDeviceId;

    private static final long TIMEOUT = 60; // seconds

    private static OkHttpClient getOkHttpClient(Context context) {
        if (androidDeviceId == null) {
            @SuppressLint("HardwareIds")
            String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            androidDeviceId = id;
        }

        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("device_type", "android")
                            .header("device_id", androidDeviceId)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .retryOnConnectionFailure(true)
                .build();
    }

    public static ApiSet getApiService(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIData.BASE_URL)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiSet.class);
    }
}
