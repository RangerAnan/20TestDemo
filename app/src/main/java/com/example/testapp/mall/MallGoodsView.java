package com.example.testapp.mall;

import android.content.Context;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.testapp.R;
import com.example.testapp.mall.model.GoodsTabInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * author：fcl
 * date：2019/12/26
 * description： 商品列表
 */
public class MallGoodsView extends LinearLayout {

    private String TAG = MallGoodsView.class.getSimpleName();
    private TabLayout tabLayout;
    private AutoHeightViewPager viewPager;
    private MallGoodsPageAdapter pageAdapter;
    private GoodsTabInfo goodsTabInfo;
    private int rvPosition;

    public MallGoodsView(Context context, GoodsTabInfo goodsTabInfo, int rvPosition) {
        super(context);
        this.goodsTabInfo = goodsTabInfo;
        this.rvPosition = rvPosition;

        initView(context);
    }

    public MallGoodsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MallGoodsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_mall_goods, null);
        setOrientation(LinearLayout.VERTICAL);
        addView(view);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpager);
        //viewPager id不重复
        viewPager.setId(View.generateViewId());

        //set viewpager
        pageAdapter = new MallGoodsPageAdapter(((AppCompatActivity) context).getSupportFragmentManager(), context, goodsTabInfo);
        viewPager.setAdapter(pageAdapter);

        //手动绑定
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.setScrollPosition(i, 0f, true);

                Log.i("MallGoodsView", "onPageSelected ");
                viewPager.resetHeight(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //自定义指示器
        setIndicator(context);

        viewPager.resetHeight(0);

    }

    private void setIndicator(Context context) {
        for (int i = 0; i < goodsTabInfo.getTabNameList().size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_mall_goods_tab_indicator, null);
            TabLayout.Tab tab = tabLayout.newTab().setCustomView(view);

            TextView tv_indicator = view.findViewById(R.id.tv_indicator);
            tv_indicator.setText(goodsTabInfo.getTabNameList().get(i));
            tabLayout.addTab(tab);
        }
    }

    public class MallGoodsPageAdapter extends FragmentPagerAdapter {
        Context context;
        GoodsTabInfo goodsTabInfo;


        public MallGoodsPageAdapter(FragmentManager fm, Context context, GoodsTabInfo goodsTabInfo) {
            super(fm);
            this.context = context;
            this.goodsTabInfo = goodsTabInfo;
        }

        @Override
        public Fragment getItem(int position) {
            GoodsInfoFragment fragment = new GoodsInfoFragment(viewPager);
            Bundle bundle = new Bundle();
            bundle.putInt("tabPosition", position);
            bundle.putInt("rvPosition", rvPosition);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return goodsTabInfo.getTabNameList().size();
        }

    }
}
