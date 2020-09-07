package com.example.gadsleaderboardmobileapp.retro;

 public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://docs.google.com/forms/u/0/d/e/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}