package com.xiaoqianxin.xiaoqianxindialog.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xiaoqianxin.xiaoqianxindialog.Constant;
import com.xiaoqianxin.xiaoqianxindialog.utils.ViewHelper;


/**
 * @author xiaoqianxin
 * @date 2018/2/25
 */

public class CircleView extends android.support.v7.widget.AppCompatImageView {

    private boolean isCheck = false;
    private Paint mPaint;
    private Context mContext;
    private int radius;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        final int center = getWidth() / 2;
        int innerCircle = getWidth() / 2 - ViewHelper.dip2px(mContext, 2);
        int ringWidth = ViewHelper.dip2px(mContext, 2);
        if (isCheck) {
            mPaint.setColor(Color.parseColor(Constant.INSTANCE.getCircleCheckColor()));
        } else {
            mPaint.setColor(Color.parseColor(Constant.INSTANCE.getCircleUnCheckColor()));
        }
        mPaint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle, mPaint);
        if (isCheck) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor(Constant.INSTANCE.getCircleCheckColor()));
            canvas.drawCircle(center, center, radius, mPaint);
        }
        super.onDraw(canvas);
    }

    public void setCheck(boolean check) {
        isCheck = check;
        if (isCheck) {
            int dius = ViewHelper.dip2px(mContext, 4);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, dius);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    radius = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.setDuration(300);
            valueAnimator.start();
        } else {
            invalidate();
        }
    }
}
