package com.example.testapp.mall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 自适应子View高度的viewPager
 *
 * @author hellsam
 */
public class AutoHeightViewPager extends ViewPager {

    private int current;
    private int height = 0;

    /**
     * 保存position与对于的View
     */
    private HashMap<Integer, View> childrenViews = new LinkedHashMap();

    public AutoHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (childrenViews.size() > current) {
            View child = childrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //切换tab的时候重新设置viewpager的高度
    public void resetHeight(int current) {
        this.current = current;
        if (childrenViews.size() > current) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    /**
     * 保存position与对应的View
     */
    public void setObjectForPosition(View view, int position) {
        childrenViews.put(position, view);
    }
}
