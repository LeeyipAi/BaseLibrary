package jmm.baselibrary.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import jmm.baselibrary.ui.fragment.BaseFragment;

/**
 * user:Administrator
 * time:2018 05 09 10:12
 * package_name:jmm.baselibrary.ui.adapter
 */
public abstract class BaseFragmentVpAdapter extends FragmentStatePagerAdapter {


    public BaseFragmentVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return createFragment(position);
    }

    public abstract Fragment createFragment(int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        initFragment(fragment, position);
        return fragment;
    }

    protected abstract void initFragment(BaseFragment fragment, int position);

    @Override
    public int getCount() {
        return getFragmentCount();
    }

    protected abstract int getFragmentCount();

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
