package com.ash.digivahan.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ash.digivahan.data.api.ApiClient;
import com.ash.digivahan.utils.AppExecutors;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class LoginRepository {
    private final Context context;

    public LoginRepository(Context context) {
        this.context = context;
    }

    public LiveData<JsonObject> login(String user, String password, boolean isOldUser) {
        MutableLiveData<JsonObject> result = new MutableLiveData<>();

        // prepare request
        String loginType = isOldUser ? "phone" : "email";

        ApiClient.getApiService(context).login(user, password, loginType).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.postValue(response.body());
                } else {
                    // Create error JSON if API fails
                    JsonObject errorObj = new JsonObject();
                    errorObj.addProperty("success", false);
                    errorObj.addProperty("message", "Login failed. Try again.");
                    result.postValue(errorObj);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                JsonObject errorObj = new JsonObject();
                errorObj.addProperty("success", false);
                errorObj.addProperty("message", t.getMessage());
                result.postValue(errorObj);
            }
        });

        return result;
    }
}