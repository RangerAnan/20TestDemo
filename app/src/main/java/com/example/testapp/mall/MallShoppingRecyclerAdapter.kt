package com.example.testapp.mall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.mall.model.MallShoppingModel
import java.util.ArrayList

/**
 * author：fcl
 * date：2019/12/24
 * description： 商城列表适配器
 */
class MallShoppingRecyclerAdapter(var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataList: MutableList<MallShoppingModel> = ArrayList()


    fun setDataList(mDataList: MutableList<MallShoppingModel>) {
        this.mDataList = mDataList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].itemType
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val manager = recyclerView.layoutManager as GridLayoutManager
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                //分成6份
                return when (mDataList[position].itemType) {
                    mall_navigation -> 1
                    mall_recommended_shop -> 2
                    else -> 6
                }
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        when (p1) {
            mall_first_banner -> {
                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_top_banner, p0, false)
                return TopBannerViewHolder(v)
            }

            mall_navigation -> {
                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_navigation, p0, false)
                return MallNavigationViewHolder(v)
            }

            mall_bottom_banner -> {
                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_bottom_banner, p0, false)
                return BottomBannerViewHolder(v)
            }

            mall_recommended_shop -> {
                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_recommend_shop, p0, false)
                return RecommendedShopViewHolder(v)
            }

            mall_goods -> {
//                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_goods, p0, false)
                val v = MallGoodsView(mContext, mDataList[p1].goodsInfo,p1)
                return MallGoodsViewHolder(v)
            }

            mall_sales_list -> {
                val v = LayoutInflater.from(mContext).inflate(R.layout.item_mall_sales_list, p0, false)
                return SalesListViewHolder(v)
            }

        }

        throw IllegalArgumentException()
    }



    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, p1: Int) {
        val model = mDataList[p1]

        when (model.itemType) {

            mall_first_banner -> {
                val holder = viewHolder as TopBannerViewHolder
            }

            mall_bottom_banner -> {
                val holder = viewHolder as BottomBannerViewHolder
            }

            mall_navigation -> {
                val holder = viewHolder as MallNavigationViewHolder
            }

            mall_recommended_shop -> {
                val holder = viewHolder as RecommendedShopViewHolder
            }

            mall_goods -> {
                val holder = viewHolder as MallGoodsViewHolder
            }

            mall_sales_list -> {
                val holder = viewHolder as SalesListViewHolder
            }
        }

    }


    /**
     * 顶部banner
     */
    class TopBannerViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }


    /**
     * 底部banner
     */
    class BottomBannerViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

    class MallNavigationViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

    class RecommendedShopViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }


    class MallGoodsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

    class SalesListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }


    companion object {

        const val mall_first_banner: Int = 0
        const val mall_navigation: Int = 1
        const val mall_bottom_banner: Int = 2

        const val mall_recommended_shop: Int = 3
//        const val mall_operation_center: Int = 5        //运营中心

//        const val mall_seckill_title: Int = 6           //秒杀
//        const val mall_seckill_item: Int = 7

        const val mall_goods: Int = 4
        const val mall_sales_list: Int = 5          //销量榜

    }
}