package com.ash.digivahan.ui.Activities.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ash.digivahan.data.repository.LoginRepository;
import com.google.gson.JsonObject;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
    }

    public LiveData<JsonObject> login(String user, String password, boolean isOldUser) {
        return repository.login(user, password, isOldUser);
    }
}

