package com.example.e_comm_home_demo_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.domain.usecase.*
import com.example.e_comm_home_demo_app.common.DataStateHandler
import com.example.e_comm_home_demo_app.common.ReponseHandler
import com.example.e_comm_home_demo_app.data.local.MyDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemoteHomeDataUseCase: GetRemoteHomeDataUseCase,

    private val showBannerImagesCountUseCase: ShowBannerImagesCountUseCase,
    private val showBannerImagesUseCase: ShowBannerImagesUseCase,
    private val showFeatureCategoryUseCase: ShowFeatureCategoryUseCase,
    private val showOurServicesUseCase: ShowOurServicesUseCase,
    private val showShopByBrandsUseCase: ShowShopByBrandsUseCase,
    private val showBanner2UseCase: ShowBanner2UseCase,
    private val showTopSellingUseCase: ShowTopSellingUseCase,
    private val showBanner2FullWidthUseCase: ShowBanner2FullWidthUseCase,
    private val showMostViewedUseCase: ShowMostViewedUseCase,
    private val showBottomSliderUseCase: ShowBottomSliderUseCase,
    private val showHotDealsUseCase: ShowHotDealsUseCase,
    private val showChosenForYouUseCase: ShowChosenForYouUseCase,

    private val myDatabase: MyDatabase

) : ViewModel() {

    private val _state = MutableStateFlow(DataStateHandler())
    val state: StateFlow<DataStateHandler> = _state

    fun clearDB() {
        viewModelScope.launch(Dispatchers.IO) {
            myDatabase.clearAllTables()
        }
    }

    fun getHomeScreenData() {
//        clearDB()
        getRemoteHomeDataUseCase().onEach { result ->
            when (result) {
                is ReponseHandler.Success -> {
                    _state.value = result.data?.let { DataStateHandler(homeDataDTO = it) }!!
                }
                is ReponseHandler.Error -> {
                    _state.value = DataStateHandler(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is ReponseHandler.Loading -> {
                    _state.value = DataStateHandler(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    val bannerImagesCount: LiveData<Int>
        get() = showBannerImagesCountUseCase.invoke()

    val bannerImagesList: LiveData<List<BannerImageDTO>>
        get() = showBannerImagesUseCase.invoke()

    val featuredCategoryList: LiveData<List<FeaturedCategoryDTO>>
        get() = showFeatureCategoryUseCase.invoke()

    val ourServicesList: LiveData<List<OurServicesDTO>>
        get() = showOurServicesUseCase.invoke()

    val shopByBrandsList: LiveData<List<ShopByBrandDTO>>
        get() = showShopByBrandsUseCase.invoke()

    val banner2List: LiveData<List<Banner2DTO>>
        get() = showBanner2UseCase.invoke()

    val topSellingList: LiveData<List<TopSellingDTO>>
        get() = showTopSellingUseCase.invoke()

    val banner2FullWidthList: LiveData<List<Banner2FullWidthDTO>>
        get() = showBanner2FullWidthUseCase.invoke()

    val mostViewedList: LiveData<List<MostViewedDTO>>
        get() = showMostViewedUseCase.invoke()

    val bottomSliderList: LiveData<List<BottomSliderDTO>>
        get() = showBottomSliderUseCase.invoke()

    val hotDealsList: LiveData<List<HotDealsDTO>>
        get() = showHotDealsUseCase.invoke()

    val chosenForYouList: LiveData<List<ChosenForYouDTO>>
        get() = showChosenForYouUseCase.invoke()
}