package com.zia.gestureviewdemo.GestureView;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zia on 2018/1/2.
 */

class ScaleHelper {

    private ArrayList<View> viewArrayList;//需要缩放的view集合
    private DisplayMetrics displayMetrics;

    ScaleHelper(Context context) {
        viewArrayList = new ArrayList<>();
        displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    void addScaleView(View view) {
        viewArrayList.add(view);
    }

    /**
     * 缩放，没有动画
     *
     * @param distanceY 缩放的绝对大小，y轴上移动的距离
     * @param speed     速度
     */
    void scale(float distanceY, int speed) {
        for (View it : viewArrayList) {

            float minScaleSize = 0.05f;
            if (it.getScaleX() <= minScaleSize) {
                it.setVisibility(View.INVISIBLE);
            } else it.setVisibility(View.VISIBLE);

            float scale = -distanceY / displayMetrics.heightPixels * speed;
            if ((scale > 0 && it.getScaleX() < 1f) || (scale <= 0 && it.getScaleX() > minScaleSize)) {
                it.setScaleX(it.getScaleX() + scale);
                it.setScaleY(it.getScaleY() + scale);
            }
            if (it.getScaleX() > 1f) {
                it.setScaleX(1f);
                it.setScaleY(1f);
            }
        }
    }

    /**
     * 动画缩放
     *
     * @param speedY   y轴滑动的速度
     * @param duration 动画时间
     * @param tagY     执行动画的最小y轴速度
     */
    void scaleAnim(float speedY, int duration, int tagY) {
        if (speedY > tagY || speedY < -tagY) {
            for (View it : viewArrayList) {
                float scale;
                if (speedY > 0) scale = 1f;
                else scale = 0f;
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(it, "scaleX", it.getScaleX(), scale);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(it, "scaleY", it.getScaleY(), scale);
                animatorX.setDuration(duration).start();
                animatorY.setDuration(duration).start();
            }
        }
    }
}
