package com.example.e_comm_home_demo_app.domain.model

data class HomeData(
    val ShopByBrands: List<ShopByBrand>,//4
    val bannerImages: List<BannerImage>,//0
    val carousel: List<Carousel>,//3
//    val categories: List<Category>,
//    val cmsData: List<CmsData>,
    val featuredCategories: List<FeaturedCategory>,//2
    val message: String,
//    val priceFormat: PriceFormat,
//    val sort_order: List<SortOrder>,
//    val storeData: List<StoreData>,
    val success: Boolean,
)