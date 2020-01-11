package com.example.testapp.mall;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;


import java.util.List;

/**
 * 自定义gridView行数
 * Created by wzy on 2015/12/8
 */
public class ScrollGridView extends GridView {

    private int leftMargin;
    private int rightMargin;
    private int colWidth = 2; // 列的间距
    private GestureDetector gestureDetector;

    public ScrollGridView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new Yscroll());
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new Yscroll());
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        gestureDetector = new GestureDetector(context, new Yscroll());
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && gestureDetector.onTouchEvent(ev);
    }

    class Yscroll extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceY) >= Math.abs(distanceX);
        }
    }
}