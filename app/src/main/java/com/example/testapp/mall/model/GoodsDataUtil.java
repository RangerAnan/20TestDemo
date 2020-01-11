package com.example.testapp.mall.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：fcl
 * date：2019/12/28
 * description：
 */
public class GoodsDataUtil {


    private static HashMap<Integer, GoodsTabInfo> goodsInfoList = new HashMap<>();


    public static List<GoodsPageModel> getGoodsPageData(int position, int tab) {


        GoodsTabInfo goodsTabInfo = goodsInfoList.get(position);
        if (goodsTabInfo != null) {
            List<GoodsInfo> goodsList = goodsTabInfo.getGoodsList();
            if (goodsList.size() > tab) {
                return goodsList.get(tab).getGoodsPageList();
            }
        }

        return new ArrayList<>();
    }

    /**
     * 更新某个tab下的商品列表
     */

    public static void setGoodsPageData(int tabPosition, List<GoodsPageModel> pageModelList) {

    }


    public static void setGoodsPageData(int position, GoodsTabInfo goodsTabInfo) {
        goodsInfoList.put(position, goodsTabInfo);
    }

}
