package jmm.baselibrary.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import jmm.baselibrary.R;
import jmm.baselibrary.utils.UIUtils;

/**
 * user:Administrator
 * time:2018 05 04 9:58
 * package_name:jmm.baselibrary.widgets
 */
public class BottomNavBar extends BottomNavigationBar {

    private TextBadgeItem mNoticeBadge;
    private BottomNavigationItem mHomeItem;
    private BottomNavigationItem mNoticeItem;
    private BottomNavigationItem mOrderItem;
    private BottomNavigationItem mUserItem;

    public BottomNavBar(Context context) {
        this(context, null);
    }

    public BottomNavBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHomeItem = new BottomNavigationItem(R.drawable.btn_nav_home_press, UIUtils.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mNoticeItem = new BottomNavigationItem(R.drawable.btn_nav_msg_press, UIUtils.getString(R.string.nav_bar_notice))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mOrderItem = new BottomNavigationItem(R.drawable.btn_nav_cart_press, UIUtils.getString(R.string.nav_bar_order))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mUserItem = new BottomNavigationItem(R.drawable.btn_nav_user_press, UIUtils.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mNoticeBadge = new TextBadgeItem();
        //设置底部导航模式及样式
        setMode(BottomNavigationBar.MODE_FIXED);
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        setBarBackgroundColor(R.color.common_white);

        //添加Tab
        addItem(mHomeItem)
                .addItem(mNoticeItem)
                .addItem(mOrderItem)
                .addItem(mUserItem)
                .setFirstSelectedPosition(0)
                .initialise();
    }

    public void cherckNoticeBadge(int count) {
        mNoticeItem.setBadgeItem(mNoticeBadge);
        if (count == 0){
            mNoticeBadge.hide();
        }else if (count > 99){
            mNoticeBadge.show();
            mNoticeBadge.setText("99+");
        }else{
            mNoticeBadge.show();
            mNoticeBadge.setText(String.valueOf(count));
        }
    }

}
