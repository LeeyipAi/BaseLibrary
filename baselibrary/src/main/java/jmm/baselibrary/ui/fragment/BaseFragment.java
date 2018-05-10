package jmm.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jmm.baselibrary.rx.rxbus2.RxBus;

/**
 * user:Administrator
 * time:2018 05 04 10:45
 * package_name:jmm.baselibrary.ui.fragment
 */
public abstract class BaseFragment extends RxFragment {

    private Unbinder mBind;
    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutId(), null);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this);
        }
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();


    //通过fragmentmanger add hide 次方法不调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("setUserVisibleHint","Lazy:" + isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    /**
     * 进行懒加载
     */
    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        Log.e("lazyFetchDataIfPrepared","Lazy:  " + getUserVisibleHint());
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            Log.e("lazyFetchData","Lazy");
            lazyFetchData();
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }
        mBind.unbind();
    }
}
