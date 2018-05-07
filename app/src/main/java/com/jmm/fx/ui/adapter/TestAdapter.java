package com.jmm.fx.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jmm.fx.R;
import com.jmm.fx.test.IntegralDetails;

import jmm.baselibrary.ui.adapter.BaseRvAdapter;
import jmm.baselibrary.utils.TimeUtils;

/**
 * user:Administrator
 * time:2018 05 07 14:06
 * package_name:com.jmm.fx.ui.adapter
 */
public class TestAdapter extends BaseRvAdapter<IntegralDetails.Data.Entity> {

    public TestAdapter() {
        super(R.layout.item_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralDetails.Data.Entity item) {
        helper.setText(R.id.tv_name, item.getCHANNEL());
        TextView tvIntegral = helper.getView(R.id.tv_integral);
        tvIntegral.setText(item.getINTEGRAL_NUM());
        helper.setText(R.id.tv_date, TimeUtils.timeToString(item.getCREATE_DATE()));
    }
}
