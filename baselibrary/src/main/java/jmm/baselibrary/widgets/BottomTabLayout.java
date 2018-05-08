package jmm.baselibrary.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import jmm.baselibrary.R;
import jmm.baselibrary.bean.TabEntity;
import jmm.baselibrary.utils.UIUtils;

/**
 * user:Administrator
 * time:2018 05 08 16:04
 * package_name:jmm.baselibrary.widgets
 */
public class BottomTabLayout extends CommonTabLayout {

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
        初始化
     */
    private void init() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity(UIUtils.getString(R.string.nav_bar_home), R.drawable.btn_nav_home_press, R.drawable.btn_nav_home_normal));
        mTabEntities.add(new TabEntity(UIUtils.getString(R.string.nav_bar_notice), R.drawable.btn_nav_msg_press, R.drawable.btn_nav_msg_normal));
        mTabEntities.add(new TabEntity(UIUtils.getString(R.string.nav_bar_order), R.drawable.btn_nav_order_press, R.drawable.btn_nav_order_normal));
        mTabEntities.add(new TabEntity(UIUtils.getString(R.string.nav_bar_user), R.drawable.btn_nav_user_press, R.drawable.btn_nav_user_normal));
        setTabData(mTabEntities);
    }


    public void setCtlBadge(int positon, int count) {
        getMsgView(positon).setBackgroundColor(UIUtils.getColor(R.color.common_blue_light));
        if (count > 0 && count < 10) {
            showMsg(positon, count);
            setMsgMargin(positon, -5, 5);
        } else if (count > 9 & count < 100) {
            showMsg(positon, count);
            setMsgMargin(positon, -8, 5);
        } else if (count > 99) {
            showMsg(positon, count);
            setMsgMargin(positon, -10, 5);
        } else {
            hideMsg(positon);
        }
    }


}
