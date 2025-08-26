package com.ash.digivahan.data.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSet extends APIData {

    // Check App Version
    @FormUrlEncoded
    @POST(GET_VERSION)
    Call<JsonObject> getVersion(@Field("get_version") String get_version);

    // Save App Version
    @FormUrlEncoded
    @POST(SET_APP_VERSION)
    Call<JsonObject> setVersion(
            @Field("user_id") String user_id,
            @Field("app_version") String app_version,
            @Field("device_name") String device_name,
            @Field("type") String type
    );

    // Login
    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("login_type") String loginType// phone or email
    );

}

