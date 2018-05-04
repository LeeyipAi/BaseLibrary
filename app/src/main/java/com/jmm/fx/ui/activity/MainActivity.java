package com.jmm.fx.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jmm.fx.R;
import com.jmm.fx.ui.fragment.NoticFragment;
import com.jmm.fx.ui.fragment.OrderFragment;
import com.jmm.fx.ui.fragment.ProductsFragment;
import com.jmm.fx.ui.fragment.UserFragment;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmm.baselibrary.ui.activity.BaseActivity;
import jmm.baselibrary.utils.ActivityUtils;
import jmm.baselibrary.utils.ToastUtils;
import jmm.baselibrary.widgets.BottomNavBar;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_contaier)
    FrameLayout mFlContaier;
    @BindView(R.id.BottomNavBar)
    BottomNavBar mBottomNavBar;
    private Stack<Fragment> mStack = new Stack<Fragment>();

    private long pressTime = 0L;

    private ProductsFragment mProductsFragment = new ProductsFragment();
    private NoticFragment mNoticFragment = new NoticFragment();
    private OrderFragment mOrderFragment = new OrderFragment();
    private UserFragment mUserFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initBottomNav();
        changeFragment(0);
        loadNoticSize();
    }

    /*
        初始化Fragment栈管理
    */
    private void initFragment() {
        FragmentTransaction mManger = getSupportFragmentManager().beginTransaction();

        mManger.add(R.id.fl_contaier, mProductsFragment);
        mManger.add(R.id.fl_contaier, mNoticFragment);
        mManger.add(R.id.fl_contaier, mOrderFragment);
        mManger.add(R.id.fl_contaier, mUserFragment);
        mManger.commit();

        mStack.add(mProductsFragment);
        mStack.add(mNoticFragment);
        mStack.add(mOrderFragment);
        mStack.add(mUserFragment);
    }

    /*
        初始化底部导航栏
     */
    private void initBottomNav() {

        mBottomNavBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                changeFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /*
        导航栏切换
     */
    private void changeFragment(int position) {
        FragmentTransaction mManger = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : mStack) {
            mManger.hide(fragment);
        }
        mManger.show(mStack.get(position));
        mManger.commit();
    }

    /*
        加载通知数量
     */
    private void loadNoticSize() {
        mBottomNavBar.cherckNoticeBadge(0);
    }

    /*

     */
    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - pressTime > 2000) {
            ToastUtils.showShort("再按一次退出程序");
            pressTime = time;
        }else {
            ActivityUtils.removeAllActivity(this);
        }
    }
}
