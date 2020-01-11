package com.example.testapp.mall;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.mall.model.GoodsDataUtil;
import com.example.testapp.mall.model.GoodsPageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * author：fcl
 * date：2019/12/28
 * description：
 */
public class GoodsInfoFragment extends Fragment {

    private Context context;
    private int tabPosition;
    private int rvPosition;
    private List<GoodsPageModel> goodsInfoList;
    private AutoHeightViewPager viewPager;
    private ScrollGridView gridView;

    public GoodsInfoFragment(AutoHeightViewPager viewPager) {
        this.viewPager = viewPager;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = LayoutInflater.from(context).inflate(R.layout.item_mall_goods_list, container, false);
        initView(view);
        viewPager.setObjectForPosition(view, tabPosition);

        return view;
    }

    private void initView(View view) {
        Bundle bundle = getArguments();
        tabPosition = bundle.getInt("tabPosition");
        rvPosition = bundle.getInt("rvPosition");
        goodsInfoList = GoodsDataUtil.getGoodsPageData(rvPosition, tabPosition);
        Log.i("GoodsInfoFragment", "initView ---goodsInfoList: " + goodsInfoList.size() + ";tabPosition:" + tabPosition + ";rvPosition:" + rvPosition);

        gridView = view.findViewById(R.id.gridview);
        gridView.setAdapter(new GoodsAdapter(goodsInfoList));


        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                if (tabPosition == 0) {
                    viewPager.resetHeight(0);
                }
                return false;
            }
        });
    }


    protected class GoodsAdapter extends AbsAdapter<GoodsPageModel, GoodsAdapter.ViewHolder> {


        public GoodsAdapter(List<GoodsPageModel> dataList) {
            super(dataList);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_mall_goods_detail;
        }

        @Override
        protected GoodsAdapter.ViewHolder getViewHolder(View itemView) {
            return new GoodsAdapter.ViewHolder(itemView);
        }

        @Override
        protected void setListener(GoodsAdapter.ViewHolder holder, int position) {

        }

        @Override
        protected void fillData(GoodsAdapter.ViewHolder holder, GoodsPageModel model) {
//            Log.i("GoodsInfoFragment", " GoodsAdapter-- fillData " + model.getGoodsName());
            holder.tvTitle.setText(model.getGoodsName());
        }

        public class ViewHolder {

            private TextView tvTitle;

            public ViewHolder(View itemView) {
                tvTitle = itemView.findViewById(R.id.tvTitle);

            }
        }
    }
}
