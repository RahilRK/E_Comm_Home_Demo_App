package com.example.e_comm_home_demo_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [
        BannerImageDTO::class,
        FeaturedCategoryDTO::class,
        OurServicesDTO::class,
        ShopByBrandDTO::class,
        Banner2DTO::class,
        TopSellingDTO::class,
        Banner2FullWidthDTO::class,
        MostViewedDTO::class,
        BottomSliderDTO::class,
        HotDealsDTO::class,
        ChosenForYouDTO::class,
               ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getMyDao(): MyDao

    class Callback @Inject constructor(
        private val database: Provider<MyDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}