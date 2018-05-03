package jmm.baselibrary.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import jmm.baselibrary.R;


/**
 * user:Administrator
 * time:2018 04 17 10:54
 * package_name:com.jmm.csg.widget
 */

public class ProgressLoading extends Dialog {

    private AnimationDrawable mAnimation;

    public ProgressLoading(@NonNull Context context) {
        super(context, R.style.LightProgressDialog);
        init();
    }

    public ProgressLoading(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(),R.layout.progress_dialog, null);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.2f;
        window.setAttributes(params);
        setCanceledOnTouchOutside(false);
        setContentView(view);
        ImageView mIvLoading = findViewById(R.id.iv_loading);
        mAnimation = (AnimationDrawable) mIvLoading.getBackground();
    }

    public void showLoading() {
        super.show();
        mAnimation.start();
    }

    public void dismissLoading() {
        super.dismiss();
        mAnimation.stop();
    }
}
