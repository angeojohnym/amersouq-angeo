package com.shopping.techxform.amersouq.RetrofitHelpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.shopping.techxform.amersouq.AmerSouqApplication;
import com.shopping.techxform.amersouq.Utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://amersouq.com/api/";
    public static final String BASE_URL_RIDE = "http://159.65.137.243/util_files/";
    //    "http://app.reachyourdestinations.com/amer_souq/"
    private static Retrofit retrofit = null;
    private static Retrofit retrofit_new = null;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit getClient() {

        final String token=AmerSouqApplication.context
                .getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE)
                .getString(Constants.user_token, "");

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", AmerSouqApplication.context
                                .getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE)
                                .getString(Constants.user_token, token)); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRideClient() {

        final String token=AmerSouqApplication.context
                .getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE)
                .getString(Constants.user_token, "");
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", AmerSouqApplication.context
                                .getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE)
                                .getString(Constants.user_token, token)); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        if (retrofit_new == null) {
            retrofit_new = new Retrofit.Builder()
                    .baseUrl(BASE_URL_RIDE)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit_new;
    }


}

