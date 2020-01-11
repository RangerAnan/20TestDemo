package com.example.testapp.mall.model


/**
 * author：fcl
 * date：2019/12/24
 * description：
 */
data class MallShoppingModel(
        val itemType: Int,          //列表类型
        val goodsInfo: GoodsTabInfo?
)


//楼层tab类型商品
data class GoodsTabInfo(
        val tabNameList: List<String>,
        val goodsList: List<GoodsInfo>

)

//每个tab下的商品列表
data class GoodsInfo(
        val goodsPageList: List<GoodsPageModel>
)


//商品详情
data class GoodsPageModel(
        val goodsName: String
)