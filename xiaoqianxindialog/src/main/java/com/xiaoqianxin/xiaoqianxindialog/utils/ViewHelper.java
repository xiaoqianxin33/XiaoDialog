package com.xiaoqianxin.xiaoqianxindialog.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * @author max
 * @name ViewHelper
 * @description 常用的系统控件操作，与业务相关
 * @date 2012-12-11
 */
public class ViewHelper {

    /**
     * 取得屏幕尺寸 add by max [2013-1-21]
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }


    /**
     * 取得屏幕尺寸 add by max [2013-5-11]
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] outHsv = new float[3];
        // 把 ARGB 转换成 HSV
        Color.colorToHSV(startValue, startHsv);
        Color.colorToHSV(endValue, endHsv);
        // 计算当前动画完成度（fraction）所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180) {
            endHsv[0] -= 360;
        } else if (endHsv[0] - startHsv[0] < -180) {
            endHsv[0] += 360;
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
        if (outHsv[0] > 360) {
            outHsv[0] -= 360;
        } else if (outHsv[0] < 0) {
            outHsv[0] += 360;
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;
        // 计算当前动画完成度（fraction）所对应的透明度
        int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);
        // 把 HSV 转换回 ARGB 返回
        return Color.HSVToColor(alpha, outHsv);
    }

    public static ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = animatedValue;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, int scale) {
        Bitmap bmp = Bitmap.createBitmap(60, 60, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAlpha(scale);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    public static int getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }



}
