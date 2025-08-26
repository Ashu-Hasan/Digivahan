package com.ash.digivahan.data.api;

public interface APIData {

    // Base URLs
    String BASE_URL = "https://jobplan.in/api/v1/";
    String IMAGE_BASE_URL = "https://jobplan.in/";
    String IMAGE_UPLOAD_URL = "https://jobplan.in/upload/";

    // Endpoints
    String PROVIDER_LOGIN = "provider_login";
    String PROVIDER_PROFILE = "provider_profile";
    String PROVIDER_PROFILE_UPDATE = "provider_profile_update";
    String PROVIDER_JOBS = "provider_jobs";
    String REPOST_JOB = "repost_job";
    String GET_VERSION = "get_version";
    String SET_APP_VERSION = "set_app_version";
}
