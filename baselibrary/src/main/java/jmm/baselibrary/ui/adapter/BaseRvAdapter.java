package jmm.baselibrary.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class BaseRvAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BaseRvAdapter(List<T> data) {
        super(data);
    }

}
