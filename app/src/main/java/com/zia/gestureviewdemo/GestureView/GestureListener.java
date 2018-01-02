package com.zia.gestureviewdemo.GestureView;

import android.view.MotionEvent;

/**
 * Created by zia on 2018/1/2.
 * 接口
 */

public class GestureListener {

    public interface OnDownListener{
        void onDown(MotionEvent motionEvent);
    }

    public interface OnShowPressListener{
        void onShowPress(MotionEvent motionEvent);
    }

    public interface OnSingleTapUpListener{
        void onSingleTapUp(MotionEvent motionEvent);
    }

    public interface OnScrollListener{
        void onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1);
    }

    public interface OnLongPressListener{
        void onLongPress(MotionEvent motionEvent);
    }

    public interface OnFlingListener{
        void onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1);
    }
}
