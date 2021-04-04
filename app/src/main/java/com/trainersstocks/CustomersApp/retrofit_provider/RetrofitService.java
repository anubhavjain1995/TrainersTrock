package com.trainersstocks.CustomersApp.retrofit_provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {


    public static String BASE_URL = "http://trainersstocks.com/api/admin/";
    public static String BASE_URL2 = "http://trainersstocks.com/api/admin";
    public static String BASE_URL1 = "http://trainersstocks.com/api/";

    public static RetrofitApiClient RetrofitService() {

        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();
        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().addInterceptor(mHttpLoginInterceptor).readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        /*if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }
*/


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

/*

        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);
*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RetrofitApiClient.class);
    }
    public static RetrofitApiClient RetrofitService2() {

        /*ConnectivityManager cm = (ConnectivityManager) Root.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null && !cm.isActiveNetworkMetered()) {
            Toast.makeText(Root.getAppContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();
        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);
        */

        /*if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }
*/


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient RetrofitService1() {

        /*ConnectivityManager cm = (ConnectivityManager) Root.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null && !cm.isActiveNetworkMetered()) {
            Toast.makeText(Root.getAppContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();
        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);
        */

        /*if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }
*/


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RetrofitApiClient.class);
    }


    public static MultipartBody.Part getFilePart(String key, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }

    public static RequestBody getRequestBody(String data) {
        return RequestBody.create(MultipartBody.FORM, data);
    }

}