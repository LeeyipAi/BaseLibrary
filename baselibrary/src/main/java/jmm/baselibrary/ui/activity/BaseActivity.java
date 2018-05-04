package jmm.baselibrary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import jmm.baselibrary.utils.ActivityUtils;

/**
 * user:Administrator
 * time:2018 05 03 15:44
 * package_name:jmm.baselibrary.ui.activity
 */
public class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }

    private View getContentView(){
        FrameLayout content = findViewById(android.R.id.content);
        return content.getChildAt(0);
    }

}
