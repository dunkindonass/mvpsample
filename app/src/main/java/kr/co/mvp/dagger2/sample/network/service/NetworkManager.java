package kr.co.mvp.dagger2.sample.network.service;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by imcheol-u on 2017. 1. 2..
 */

public class NetworkManager {
    private static NetworkManager instance;

    static Context mContext;
    public static NetworkManager getInstance(Context context){
        if(instance ==null){
            instance=new NetworkManager();
        }
        return instance;
    }



    final int DAUM = 0;
    final int DEV = 1;
    private String[] BASEURL = {"http://nphone.daum.net", "http://172.16.30.55:8087"};


    public Converter.Factory  converterFactory(){
        return GsonConverterFactory.create();
    }


    public CallAdapter.Factory callAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(new OkHttpInterceptor()).build();
    }

    public Retrofit retrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(converterFactory())
                .client(okHttpClient())
                .baseUrl(BASEURL[DAUM])
                .addCallAdapterFactory(callAdapterFactory())
                .build();
        return retrofit;
    }



    public NetworkApi kumonNetworkService(){
        return retrofit().create(NetworkApi.class);
    }



}
