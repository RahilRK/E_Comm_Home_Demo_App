package com.example.e_comm_home_demo_app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.e_comm_home_demo_app.data.model.*

@Dao
interface MyDao {


    /*todo saveData*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBannerImages(bannerImageDTO: BannerImageDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategory(featuredCategoryDTO: FeaturedCategoryDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOurServices(ourServicesDTO: OurServicesDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShopByBrands(shopByBrandDTO: ShopByBrandDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBanner2(banner2DTO: Banner2DTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTopSelling(topSellingDTO: TopSellingDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBanner2FullWidth(banner2FullWidthDTO: Banner2FullWidthDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMostViewed(mostViewedDTO: MostViewedDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBottomSlider(bottomSliderDTO: BottomSliderDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChosenForYou(chosenForYouDTO: ChosenForYouDTO) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHotDeals(hotDealsDTO: HotDealsDTO) : Long

    /*todo getData*/
    @Query("select count(id) as count from bannerImages")
    fun getBannerImageCount(): LiveData<Int>

    @Query("select * from bannerImages")
    fun getBannerImage(): LiveData<List<BannerImageDTO>>

    @Query("select * from featuredCategories limit 8")
    fun getFeaturedCategory(): LiveData<List<FeaturedCategoryDTO>>

    @Query("select * from our_services")
    fun getOurService(): LiveData<List<OurServicesDTO>>

    @Query("select * from shop_by_brands")
    fun getShopByBrand(): LiveData<List<ShopByBrandDTO>>

    @Query("select * from banner2")
    fun getBanner2(): LiveData<List<Banner2DTO>>

    @Query("select * from top_selling limit 4")
    fun getTopSelling(): LiveData<List<TopSellingDTO>>

    @Query("select * from banner2FullWidth")
    fun getBanner2FullWidth(): LiveData<List<Banner2FullWidthDTO>>

    @Query("select * from most_viewed limit 4")
    fun getMostViewed(): LiveData<List<MostViewedDTO>>

    @Query("select * from bottomslider")
    fun getBottomSlider(): LiveData<List<BottomSliderDTO>>

    @Query("select * from chosen_for_you limit 6")
    fun getChosenForYou(): LiveData<List<ChosenForYouDTO>>

    @Query("select * from hot_deals")
    fun getHotDeals(): LiveData<List<HotDealsDTO>>
}