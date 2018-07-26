package com.xiaoqianxin.xiaoqianxindialog.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.xiaoqianxin.xiaoqianxindialog.listener.DialogListener;

/**
 * @author xiaoqianxin
 * @date 2018/1/12
 * 基类dialog
 */

public abstract class BaseDialog extends Dialog {

    public Context mContext;

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    /**
     * 初始化方法
     */
    protected abstract void init();

    /**
     * 设置内容
     *
     * @param content 多条内容
     * @return 返回本对象
     */
    public abstract BaseDialog setContent(Object content);

    /**
     * 设置监听
     *
     * @param listener 监听
     * @return 返回本对象
     */
    public abstract BaseDialog setListener(DialogListener listener);

}
