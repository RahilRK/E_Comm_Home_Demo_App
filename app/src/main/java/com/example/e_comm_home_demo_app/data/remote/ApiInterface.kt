package com.example.e_comm_home_demo_app.data.remote

import com.example.e_comm_home_demo_app.data.model.HomeDataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("mobikulhttp/catalog/homepagedata")
    suspend fun getHomeScreenData(
        @Query("eTag") eTag: String = "df12aa2a50167a27dd67b6104e644464abcdd64c95fe5ab6615827b16c1cc437",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("quoteId") quoteId: String = "0",
        @Query("customerToken") customerToken: String = "",
        @Query("currency") currency: String = "SAR",
        @Query("width") width: String = "1080",
        @Query("mFactor") mFactor: String = "2.625",
        @Query("isFromUrl") isFromUrl: String = "0",
        @Query("url") url: String = "",
        @Query("is_home_brands") is_home_brands: String = "1",
    ): Response<HomeDataDTO>

}