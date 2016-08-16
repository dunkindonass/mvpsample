package kr.co.mvp.dagger2.sample.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;
import kr.co.mvp.dagger2.sample.network.service.OkHttpInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 8454 on 2016-08-03.
 */
@Module
public class NetworkModule {
    final int DAUM = 0;
    final int DEV = 1;
    private String[] BASEURL = {"http://nphone.daum.net", "http://172.16.30.55:8087"};


    @Provides
    @Singleton
    public Converter.Factory  converterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory callAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    @Provides
    @Singleton
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(new OkHttpInterceptor()).build();
    }


    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient client, Converter.Factory convertFactory, CallAdapter.Factory callAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(convertFactory)
                .client(client)
                .baseUrl(BASEURL[DAUM])
                .addCallAdapterFactory(callAdapterFactory)
                .build();
        return retrofit;
    }


    @Provides
    @Singleton
    public NetworkApi kumonNetworkService(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }
}
