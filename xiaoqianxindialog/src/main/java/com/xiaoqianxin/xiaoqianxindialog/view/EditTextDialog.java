package com.xiaoqianxin.xiaoqianxindialog.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaoqianxin.xiaoqianxindialog.R;
import com.xiaoqianxin.xiaoqianxindialog.base.BaseDialog;
import com.xiaoqianxin.xiaoqianxindialog.listener.DialogListener;
import com.xiaoqianxin.xiaoqianxindialog.listener.EditTextListener;
import com.xiaoqianxin.xiaoqianxindialog.utils.KeyboardUtils;

import java.util.HashMap;


/**
 * 编辑单项内容dialog
 *
 * @author xiaoqianxin
 * @date 2017/5/31
 */

public class EditTextDialog extends BaseDialog {

    private TextView mTvTitle;
    private EditText mEtInput;
    private EditTextListener mOnEditOkClickListener;

    public EditTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void init() {
        View inflate = View.inflate(mContext, R.layout.dialog_edit_name, null);
        setContentView(inflate);
        mTvTitle = inflate.findViewById(R.id.tv_title);
        mEtInput = inflate.findViewById(R.id.et_input);
        Button btnCancel = inflate.findViewById(R.id.btn_cancel);
        Button btnOk = inflate.findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEtInput.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    if (!TextUtils.isEmpty(input)) {
                        mOnEditOkClickListener.clickOk(input);
                    } else {
                        mOnEditOkClickListener.clickOk("");
                    }
                }
                dismiss();
            }
        });
        mEtInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyboardUtils.showSoftInput(mEtInput, mContext);
            }
        }, 300);
    }

    @Override
    public BaseDialog setContent(Object content) {
        HashMap<String, String> map = (HashMap<String, String>) content;
        String title = map.get("title");
        setTitle(title);
        String content1 = map.get("content");
        setContent(content1);
        return this;
    }

    @Override
    public BaseDialog setListener(DialogListener listener) {
        mOnEditOkClickListener = (EditTextListener) listener;
        return this;
    }


    private void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    private void setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            mEtInput.setText(content);
            mEtInput.setSelection(content.length());
        }
    }

    @Override
    public void show() {
        super.show();
    }

}
