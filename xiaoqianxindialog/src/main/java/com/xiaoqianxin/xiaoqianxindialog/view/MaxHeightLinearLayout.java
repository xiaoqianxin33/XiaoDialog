package com.xiaoqianxin.xiaoqianxindialog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xiaoqianxin.xiaoqianxindialog.R;
import com.xiaoqianxin.xiaoqianxindialog.utils.ViewHelper;


/**
 * @author xiaoqianxin
 * @date 2018/2/26
 */

public class MaxHeightLinearLayout extends LinearLayout {

    private int maxHeight = ViewHelper.dip2px(getContext(), 330);

    public MaxHeightLinearLayout(Context context) {
        super(context);
    }

    public MaxHeightLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightLinearLayout);
        maxHeight = (int) typedArray.getDimension(R.styleable.MaxHeightLinearLayout_maxHeight, maxHeight);
        typedArray.recycle();
    }

    public MaxHeightLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (size > maxHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
