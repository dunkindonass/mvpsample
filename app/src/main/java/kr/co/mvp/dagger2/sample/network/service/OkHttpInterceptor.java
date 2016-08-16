package kr.co.mvp.dagger2.sample.network.service;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by 8454 on 2016-04-28.
 */
public class OkHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Sink sink = Okio.sink(baos);
        BufferedSink bufferedSink = Okio.buffer(sink);

        /**
         * Write old params
         * */
        if(originalRequest.body()!=null){
            originalRequest.body().writeTo(bufferedSink);
            Log.e("requestBody::",bufferedSink.buffer().readUtf8());
        }else{
            Log.e("getType Url::",originalRequest.url().uri().toString());
        }

        Request newRequest = originalRequest.newBuilder()
                .build();

        return chain.proceed(newRequest);
    }
}
