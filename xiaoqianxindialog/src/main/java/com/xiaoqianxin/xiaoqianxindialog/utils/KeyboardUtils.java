package com.xiaoqianxin.xiaoqianxindialog.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘相关工具类
 *
 * @author xiaoqianxin
 * @date 2017/3/24
 */

public class KeyboardUtils {

    /**
     * 动态隐藏软键盘
     *
     * @param activity activity
     */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 动态显示软键盘
     *
     * @param edit 输入框
     */
    public static void showSoftInput(EditText edit, Context activity) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.showSoftInput(edit, 0);
    }


}
