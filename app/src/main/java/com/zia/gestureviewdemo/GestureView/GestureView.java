package com.zia.gestureviewdemo.GestureView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zia on 2018/1/1.
 */

public class GestureView extends FrameLayout implements GestureDetector.OnGestureListener {

    private final static String TAG = "GestureView";
    private GestureDetector gestureDetector;//手势监测
    private ScaleHelper scaleHelper;//缩放工具
    private int scaleSpeed = 4;//速度（倍数）
    private GestureListener.OnDownListener onDownListener;
    private GestureListener.OnShowPressListener onShowPressListener;
    private GestureListener.OnSingleTapUpListener onSingleTapUpListener;
    private GestureListener.OnScrollListener onScrollListener;
    private GestureListener.OnLongPressListener onLongPressListener;
    private GestureListener.OnFlingListener onFlingListener;

    public void addOnDownListener(GestureListener.OnDownListener onDownListener) {
        this.onDownListener = onDownListener;
    }

    public void addOnShowPressListener(GestureListener.OnShowPressListener onShowPressListener) {
        this.onShowPressListener = onShowPressListener;
    }

    public void addOnSingleTapUpListener(GestureListener.OnSingleTapUpListener onSingleTapUpListener) {
        this.onSingleTapUpListener = onSingleTapUpListener;
    }

    public void addOnScrollListener(GestureListener.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void addOnLongPressListener(GestureListener.OnLongPressListener onLongPressListener) {
        this.onLongPressListener = onLongPressListener;
    }

    public void addOnFlingListener(GestureListener.OnFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }

    public void setScaleSpeed(int scaleSpeed){
        this.scaleSpeed = scaleSpeed;
    }

    /**
     * 将view与手势监听联系起来，向上滑动时缩小隐藏该view
     *
     * @param view 需要缩放的view
     */
    public void attachScaleView(View view) {
        scaleHelper.addScaleView(view);
    }

    /**
     * 重置监听器
     *
     * @param onGestureListener 监听器
     */
    public void reSetOnGestureListener(GestureDetector.OnGestureListener onGestureListener) {
        if (onGestureListener != null)
            gestureDetector = new GestureDetector(getContext(), onGestureListener);
    }

    public GestureView(Context context) {
        super(context);
        init(context);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GestureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setClickable(true);
        scaleHelper = new ScaleHelper(getContext());
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if (onDownListener != null) onDownListener.onDown(motionEvent);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        if (onShowPressListener != null) onShowPressListener.onShowPress(motionEvent);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (onSingleTapUpListener != null) onSingleTapUpListener.onSingleTapUp(motionEvent);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (onScrollListener != null) onScrollListener.onScroll(motionEvent, motionEvent1, v, v1);
        if (Math.abs(v1) >= 1f)//防抖
            scaleHelper.scale(v1, scaleSpeed);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        if (onLongPressListener != null) onLongPressListener.onLongPress(motionEvent);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (onFlingListener != null) onFlingListener.onFling(motionEvent, motionEvent1, v, v1);
        //快速滑动屏幕的操作，本来想弄个动画的，但是实际效果好像不怎么样，先注释掉
        scaleHelper.scaleAnim(v1, 500, 5000);
        return false;
    }
}
