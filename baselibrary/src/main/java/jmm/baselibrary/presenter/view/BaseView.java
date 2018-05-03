package jmm.baselibrary.presenter.view;

/**
 * user:Administrator
 * time:2018 05 03 15:59
 * package_name:jmm.baselibrary.presenter.view
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(String msg);
}
