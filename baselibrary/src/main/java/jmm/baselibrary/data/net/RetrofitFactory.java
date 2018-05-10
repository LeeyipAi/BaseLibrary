package jmm.baselibrary.data.net;

import java.util.concurrent.TimeUnit;

import jmm.LoggingInterceptor;
import jmm.baselibrary.BuildConfig;
import jmm.baselibrary.common.BaseConstant;
import jmm.baselibrary.data.net.interceptor.SignatureInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * user:HBKJ
 * time:2018 05 03 20:03
 * package_name:jmm.baselibrary.data.net
 */
public class RetrofitFactory {

    private static volatile RetrofitFactory instance = null;
    private Retrofit mRetrofit;

    private RetrofitFactory() {
        init();
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }


    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseConstant.SERVICE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClent())
                .build();
    }

    private OkHttpClient initClent() {
        return new OkHttpClient.Builder()
                .addInterceptor(new SignatureInterceptor())
                .addInterceptor(initLogInterceptor())
                .addInterceptor(commonInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    //通用拦截器
    private Interceptor commonInterceptor() {
        return chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .build();
            return chain.proceed(request);
        };
    }

    //日志拦截器
    private Interceptor initLogInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .request()
                .requestTag("Request_Okhttp")
                .response()
                .responseTag("Response_Okhttp")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build();
    }


    public <T> T creat(Class<T> service){
        return mRetrofit.create(service);
    }

}
