package com.example.gad_project;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class CallBuilder {


    private static final String baseURL  = "https://docs.google.com/forms/d/e/";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);


    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <s> s buildService(Class<s> serviceType){
        return retrofit.create(serviceType);
    }

}
