package com.jmm.fx.ui.activity;

import android.os.Bundle;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jmm.fx.R;
import com.jmm.fx.ui.adapter.MainVpAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmm.baselibrary.ui.activity.BaseActivity;
import jmm.baselibrary.utils.ActivityUtils;
import jmm.baselibrary.utils.ToastUtils;
import jmm.baselibrary.widgets.BottomTabLayout;
import jmm.baselibrary.widgets.NoScrollViewPager;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.ctl_table)
    BottomTabLayout mCtlTable;

    private long pressTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DarkTheme);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        initCommonTabLayout();
        loadNoticSize();
    }

    /**
     * 初始化底部导航栏
     */
    private void initCommonTabLayout() {
        mCtlTable.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViewpager.setAdapter(new MainVpAdapter(getSupportFragmentManager()));
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setCurrentItem(0);
    }

    /**
     * 加载通知数量
     */
    private void loadNoticSize() {
        mCtlTable.setCtlBadge(0, 0);
        mCtlTable.setCtlBadge(1, 9);
        mCtlTable.setCtlBadge(2, 99);
        mCtlTable.setCtlBadge(3, 999);
    }

    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - pressTime > 2000) {
            ToastUtils.showShort("再按一次退出程序");
            pressTime = time;
        } else {
            ActivityUtils.removeAllActivity(this);
        }
    }
}
