package com.example.e_comm_home_demo_app.data.repository

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.local.MyDao
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.data.remote.ApiInterface
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val myDao: MyDao
): DomainRepository {

    override suspend fun getHomeScreenData(): Response<HomeDataDTO> {
        return apiInterface.getHomeScreenData()
    }

    /*todo saveData*/
    override suspend fun saveBannerImages(bannerImageDTO: BannerImageDTO): Long {
        return myDao.saveBannerImages(bannerImageDTO)
    }

    override suspend fun saveCategory(featuredCategoryDTO: FeaturedCategoryDTO): Long {
        return myDao.saveCategory(featuredCategoryDTO)
    }

    override suspend fun saveOurServices(ourServicesDTO: OurServicesDTO): Long {
        return myDao.saveOurServices(ourServicesDTO)
    }

    override suspend fun saveShopByBrands(shopByBrandDTO: ShopByBrandDTO): Long {
        return myDao.saveShopByBrands(shopByBrandDTO)
    }

    override suspend fun saveBanner2(banner2DTO: Banner2DTO): Long {
        return myDao.saveBanner2(banner2DTO)
    }

    override suspend fun saveTopSelling(topSellingDTO: TopSellingDTO): Long {
        return myDao.saveTopSelling(topSellingDTO)
    }

    override suspend fun saveBanner2FullWidth(banner2FullWidthDTO: Banner2FullWidthDTO): Long {
        return myDao.saveBanner2FullWidth(banner2FullWidthDTO)
    }

    override suspend fun saveMostViewed(mostViewedDTO: MostViewedDTO): Long {
        return myDao.saveMostViewed(mostViewedDTO)
    }

    override suspend fun saveBottomSlider(bottomSliderDTO: BottomSliderDTO): Long {
        return myDao.saveBottomSlider(bottomSliderDTO)
    }

    override suspend fun saveChosenForYou(chosenForYouDTO: ChosenForYouDTO): Long {
        return myDao.saveChosenForYou(chosenForYouDTO)
    }

    override suspend fun saveHotDeals(hotDealsDTO: HotDealsDTO): Long {
        return myDao.saveHotDeals(hotDealsDTO)
    }

    override fun getBannerImageCount(): LiveData<Int> {
        return myDao.getBannerImageCount()
    }

    /*todo getData*/
    override fun getBannerImage(): LiveData<List<BannerImageDTO>> {
        return myDao.getBannerImage()
    }

    override fun getFeatureCategory(): LiveData<List<FeaturedCategoryDTO>> {
        return myDao.getFeaturedCategory()
    }

    override fun getOurService(): LiveData<List<OurServicesDTO>> {
        return myDao.getOurService()
    }

    override fun getShopByBrand(): LiveData<List<ShopByBrandDTO>> {
        return myDao.getShopByBrand()
    }

    override fun getBanner2(): LiveData<List<Banner2DTO>> {
        return myDao.getBanner2()
    }

    override fun getTopSelling(): LiveData<List<TopSellingDTO>> {
        return myDao.getTopSelling()
    }

    override fun getBanner2FullWidth(): LiveData<List<Banner2FullWidthDTO>> {
        return myDao.getBanner2FullWidth()
    }

    override fun getMostViewed(): LiveData<List<MostViewedDTO>> {
        return myDao.getMostViewed()
    }

    override fun getBottomSlider(): LiveData<List<BottomSliderDTO>> {
        return myDao.getBottomSlider()
    }

    override fun getChosenForYou(): LiveData<List<ChosenForYouDTO>> {
        return myDao.getChosenForYou()
    }

    override fun getHotDeals(): LiveData<List<HotDealsDTO>> {
        return myDao.getHotDeals()
    }
}