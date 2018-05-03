package jmm.baselibrary.utils;

import android.widget.Toast;

import jmm.baselibrary.common.BaseApplication;

public class ToastUtils {

	private static Toast mToast;

	public static void showLong(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(BaseApplication.getBaseApplication(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public static void showShort(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(BaseApplication.getBaseApplication(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

}
