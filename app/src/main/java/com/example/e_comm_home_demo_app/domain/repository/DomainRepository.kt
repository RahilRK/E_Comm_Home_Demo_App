package com.example.e_comm_home_demo_app.domain.repository

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.domain.model.ShopByBrand
import retrofit2.Response

interface DomainRepository {

    suspend fun getHomeScreenData(): Response<HomeDataDTO>

    /*todo saveData*/
    suspend fun saveBannerImages(bannerImageDTO: BannerImageDTO): Long

    suspend fun saveCategory(featuredCategoryDTO: FeaturedCategoryDTO): Long

    suspend fun saveOurServices(ourServicesDTO: OurServicesDTO): Long

    suspend fun saveShopByBrands(shopByBrandDTO: ShopByBrandDTO): Long

    suspend fun saveBanner2(banner2DTO: Banner2DTO): Long

    suspend fun saveTopSelling(topSellingDTO: TopSellingDTO): Long

    suspend fun saveBanner2FullWidth(banner2FullWidthDTO: Banner2FullWidthDTO): Long

    suspend fun saveMostViewed(mostViewedDTO: MostViewedDTO): Long

    suspend fun saveBottomSlider(bottomSliderDTO: BottomSliderDTO): Long

    suspend fun saveChosenForYou(chosenForYouDTO: ChosenForYouDTO): Long

    suspend fun saveHotDeals(hotDealsDTO: HotDealsDTO): Long

    /*todo getData*/
    fun getBannerImageCount(): LiveData<Int>

    fun getBannerImage(): LiveData<List<BannerImageDTO>>

    fun getFeatureCategory(): LiveData<List<FeaturedCategoryDTO>>

    fun getOurService(): LiveData<List<OurServicesDTO>>

    fun getShopByBrand(): LiveData<List<ShopByBrandDTO>>

    fun getBanner2(): LiveData<List<Banner2DTO>>

    fun getTopSelling(): LiveData<List<TopSellingDTO>>

    fun getBanner2FullWidth(): LiveData<List<Banner2FullWidthDTO>>

    fun getMostViewed(): LiveData<List<MostViewedDTO>>

    fun getBottomSlider(): LiveData<List<BottomSliderDTO>>

    fun getChosenForYou(): LiveData<List<ChosenForYouDTO>>

    fun getHotDeals(): LiveData<List<HotDealsDTO>>
}