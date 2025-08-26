package com.ash.digivahan.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREF_NAME = "digivahan_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_AUTH_TOKEN = "auth_token";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    public static final String KEY_FIRST_LAUNCH = "is_first_launch";


    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public PreferencesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // --- User ID ---
    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return prefs.getString(KEY_USER_ID, null);
    }

    // --- Auth Token ---
    public void setAuthToken(String token) {
        editor.putString(KEY_AUTH_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        return prefs.getString(KEY_AUTH_TOKEN, null);
    }

    // --- Login Status ---
    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, loggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Boolean
    public void setBoolean(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    // String
    public void setString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    // Integer
    public void setInt(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }

    // Long
    public void setLong(String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }


    // --- Clear all prefs ---
    public void clear() {
        editor.clear();
        editor.apply();
    }
}

