package com.xiaoqianxin.xiaoqianxindialog.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.xiaoqianxin.xiaoqianxindialog.R;
import com.xiaoqianxin.xiaoqianxindialog.base.BaseDialog;
import com.xiaoqianxin.xiaoqianxindialog.listener.DialogListener;
import com.xiaoqianxin.xiaoqianxindialog.listener.LoadingListener;

/**
 * 自定义等待dialog
 *
 * @author xiaoqianxin
 * @date 2017/7/12
 */

public class LoadingDialog extends BaseDialog {

    private TextView mTitle;
    private LoadingListener mOnClickListener;

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void init() {
        View inflate = View.inflate(mContext, R.layout.dialog_loading, null);
        setContentView(inflate);
        mTitle = inflate.findViewById(R.id.tv_title);
        TextView tvCancel = inflate.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
            }
        });
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
            }
        });
        setCancelable(false);

    }

    @Override
    public BaseDialog setContent(Object content) {
        try {
            setMsg((String) content);
        } catch (ClassCastException ignored) {
        }
        return this;
    }

    @Override
    public BaseDialog setListener(DialogListener listener) {
        try {
            mOnClickListener = (LoadingListener) listener;
        } catch (ClassCastException ignored) {
        }
        return this;
    }

    private void setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            mTitle.setText(msg);
        }
    }

}
