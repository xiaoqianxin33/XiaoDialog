package com.xiaoqianxin.xiaoqianxindialog.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianxin.xiaoqianxindialog.R;
import com.xiaoqianxin.xiaoqianxindialog.domain.SingleChoiceItem;
import com.xiaoqianxin.xiaoqianxindialog.view.CircleView;

import java.util.List;

/**
 * @author xiaoqianxin
 * @date 2018/2/25
 */

public class SingleChoiceAdapter extends BaseQuickAdapter<SingleChoiceItem, BaseViewHolder> {

    public SingleChoiceAdapter(int layoutResId, @Nullable List<SingleChoiceItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SingleChoiceItem item) {
        helper.setText(R.id.title, item.getName());
        boolean check = item.isCheck();
        helper.setTextColor(R.id.title, check ? Color.RED : Color.parseColor("#757575"));
        CircleView imageView = helper.getView(R.id.icon);
        imageView.setCheck(check);
    }
}
