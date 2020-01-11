package com.example.testapp.mall


import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.os.Bundle

import com.example.testapp.R
import com.example.testapp.mall.model.*

import java.util.ArrayList

class MallShoppingActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mContext: Context? = null
    private var adapter: MallShoppingRecyclerAdapter? = null
    private val mDataList = ArrayList<MallShoppingModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_mall_shopping)

        initView()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        //init recyclerView
        recyclerView!!.layoutManager = GridLayoutManager(mContext, 6, GridLayoutManager.VERTICAL, false)
        adapter = MallShoppingRecyclerAdapter(mContext!!)
        recyclerView!!.adapter = adapter

        adapter!!.setDataList(getData())
    }


    //模拟数据
    private fun getData(): MutableList<MallShoppingModel> {

        val topBanner = MallShoppingModel(MallShoppingRecyclerAdapter.mall_first_banner, null)
        mDataList.add(topBanner)

        val mallNavigation = MallShoppingModel(MallShoppingRecyclerAdapter.mall_navigation, null)
        mDataList.add(mallNavigation)

        val bottomBanner = MallShoppingModel(MallShoppingRecyclerAdapter.mall_bottom_banner, null)
        mDataList.add(bottomBanner)

        val recommendedShop = MallShoppingModel(MallShoppingRecyclerAdapter.mall_recommended_shop, null)
        mDataList.add(recommendedShop)


        //tab商品
        val tabNameList: MutableList<String> = ArrayList<String>()
        val goodsList: MutableList<GoodsInfo> = ArrayList<GoodsInfo>()

        for (index in 0..4) {
            val tabName = "tab-" + index
            tabNameList.add(tabName)

            val goodPageList: MutableList<GoodsPageModel> = ArrayList<GoodsPageModel>()

            for (j in 0..(5 + index * 2)) {
                val goodsListModel = GoodsPageModel(tabName + "   商品 -" + j)
                goodPageList.add(goodsListModel)
            }

            val goodsInfo = GoodsInfo(goodPageList)
            goodsList.add(goodsInfo)

        }
        val goodsModel = GoodsTabInfo(tabNameList, goodsList)


        val mallGoods = MallShoppingModel(MallShoppingRecyclerAdapter.mall_goods, goodsModel)
        mDataList.add(mallGoods)
        GoodsDataUtil.setGoodsPageData(mDataList.size - 1, goodsModel)

        //销量榜
        val salesList = MallShoppingModel(MallShoppingRecyclerAdapter.mall_sales_list, null)
        mDataList.add(salesList)





        //tab商品

        tabNameList.clear()
        goodsList.clear()

        for (index in 0..4) {
            val tabName = "tab-" + index
            tabNameList.add(tabName)

            val goodPageList: MutableList<GoodsPageModel> = ArrayList<GoodsPageModel>()

            for (j in 0..(5 + index * 2)) {
                val goodsListModel = GoodsPageModel(tabName + "   商品 -" + j)
                goodPageList.add(goodsListModel)
            }

            val goodsInfo = GoodsInfo(goodPageList)
            goodsList.add(goodsInfo)

        }
        val goodsModel2 = GoodsTabInfo(tabNameList, goodsList)


        val mallGoods2 = MallShoppingModel(MallShoppingRecyclerAdapter.mall_goods, goodsModel2)
        mDataList.add(mallGoods2)
        GoodsDataUtil.setGoodsPageData(mDataList.size - 1, goodsModel2)

        return mDataList
    }
}
