package jmm.baselibrary.rx;

import android.util.Log;

import com.google.gson.stream.MalformedJsonException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import jmm.baselibrary.utils.ToastUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException || e instanceof UnknownHostException) {
            ToastUtils.showShort("请检查当前的网络环境！");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("网络超时");
        } else if (e instanceof HttpException) {
            ToastUtils.showShort("服务器繁忙,请稍候再试!");
        }
        if (e instanceof MalformedJsonException) {
            Log.e("MalformedJsonException", "--------------->>>> JSON解析异常");
        }
        e.printStackTrace();
    }

}
