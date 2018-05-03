package jmm.baselibrary.data.net;

import com.safframework.http.interceptor.LoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jmm.baselibrary.BuildConfig;
import jmm.baselibrary.common.BaseConstant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClent())
                .build();
    }

    private OkHttpClient initClent() {
        return new OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(commonInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    //通用拦截器
    private Interceptor commonInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("charset", "utf-8")
                        .build();
                return chain.proceed(request);
            }
        };
    }

    //日志拦截器
    private Interceptor initLogInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .request()
                .requestTag("Request")
                .response()
                .responseTag("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build();
    }


    public <T> T creat(Class<T> service){
        return mRetrofit.create(service);
    }

}
