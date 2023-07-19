package com.example.e_comm_home_demo_app.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.e_comm_home_demo_app.R
import com.example.e_comm_home_demo_app.adapter.ChoseForYouAdapter
import com.example.e_comm_home_demo_app.common.CheckInternetHelper
import com.example.e_comm_home_demo_app.common.CommonRecyclerViewAdapter
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.databinding.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private var mTAG = "HomeFragment"

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private var bannerImagesList = arrayListOf<BannerImageDTO>()
    private var featureCategoryList = arrayListOf<FeaturedCategoryDTO>()
    private var ourServicesList = arrayListOf<OurServicesDTO>()
    private var shopByBrandList = arrayListOf<ShopByBrandDTO>()
    private var banner2List = arrayListOf<Banner2DTO>()
    private var topSellingList = arrayListOf<TopSellingDTO>()
    private var banner2FullWidthList = arrayListOf<Banner2FullWidthDTO>()
    private var mostViewedList = arrayListOf<MostViewedDTO>()
    private var bottomSliderList = arrayListOf<BottomSliderDTO>()
    private var chosenForYouList = arrayListOf<ChosenForYouDTO>()
    private var hotDealsList = arrayListOf<HotDealsDTO>()

    @Inject
    lateinit var checkInternetHelper: CheckInternetHelper

    private var isSwipeRefresh: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.swipeToRefresh.setOnRefreshListener(swipeToRefreshListener)
        binding.txtViewViewAllCategory.setOnClickListener(this)

        getData()
        collectHomeScreenData()

        return binding.root
    }

    /*todo check whether locally data exist in room db, and if not then call api*/
    private fun getData() {
        viewModel.bannerImagesCount.observe(
            viewLifecycleOwner
        ) { count ->
            if (!isSwipeRefresh) {
                Log.d(mTAG, "checkOfflineDataExist: $count")
                if (count > 0) {
                    getBannerImagesData()
                    binding.layoutStaticUi.visibility = View.VISIBLE
                    getFeatureCategoryData()
                    getOurServicesData()
                    getShopByBrandsData()
                    getBanner2Data()
                    getTopSellingData()
                    getBanner2FullWidthData()
                    getMostViewedData()
                    getBottomSliderData()
                    getChosenForYouData()
                    getHotDealsData()
                } else {
                    if (checkInternetHelper.isInternetAvailable()) {
                        viewModel.getHomeScreenData()
                    } else {
                        Toast.makeText(
                            activity,
                            checkInternetHelper.networkErrorMsg,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                isSwipeRefresh = false
            }
        }
    }

    /*todo handle home screen api loading, error and success*/
    private fun collectHomeScreenData() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                if (it.isLoading) {
                    if (!isSwipeRefresh) {
                        showProgress()
                    } else {
                        binding.layoutStaticUi.visibility = View.GONE
                    }
                } else if (it.error.isNotEmpty()) {
                    Log.e(mTAG, "getData Error: ${it.error}")
                    Toast.makeText(activity, R.string.something_went_wrong_msg, Toast.LENGTH_SHORT)
                        .show()
                    hideProgress()
                } else {
                    Log.d(mTAG, "getData success:")
                    hideProgress()
                }
            }
        }
    }

    /*todo observing banner images data from room db*/
    private fun getBannerImagesData() {

        viewModel.bannerImagesList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getBannerImagesData: ${list.size}")
            binding.showBannerImages = list.isNotEmpty()
            bannerImagesList.clear()
            bannerImagesList = list as ArrayList<BannerImageDTO>
            loadBannerImages()
        }
    }

    /*todo set banner images adapter to load banner images slider*/
    private fun loadBannerImages() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<BannerImageDTO, SliderImagesItemBinding>(
                requireContext(),
                bannerImagesList
            ) {
            override val layoutResId: Int
                get() = R.layout.slider_images_item

            override fun onBindData(
                model: BannerImageDTO,
                position: Int,
                dataBinding: SliderImagesItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: BannerImageDTO, position: Int) {

            }
        }

        binding.bannerImagesViewPager.apply {
            adapter = mAdapter
        }
        binding.dotsIndicator.setViewPager2(binding.bannerImagesViewPager)
    }

    /*todo observing category data from room db*/
    private fun getFeatureCategoryData() {

        viewModel.featuredCategoryList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getFeatureCategoryData: ${list.size}")
            binding.showFeatureCategory = list.isNotEmpty()
            featureCategoryList.clear()
            featureCategoryList = list as ArrayList<FeaturedCategoryDTO>
            loadFeatureCategory()
        }
    }

    /*todo set shop by category*/
    private fun loadFeatureCategory() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<FeaturedCategoryDTO, CategoryItemBinding>(
                requireContext(),
                featureCategoryList
            ) {
            override val layoutResId: Int
                get() = R.layout.category_item

            override fun onBindData(
                model: FeaturedCategoryDTO,
                position: Int,
                dataBinding: CategoryItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: FeaturedCategoryDTO, position: Int) {

            }
        }

        binding.rcyViewCategory.apply {
            adapter = mAdapter
        }
    }

    /*todo observing our services data from room db*/
    private fun getOurServicesData() {

        viewModel.ourServicesList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getOurServicesData: ${list.size}")
            binding.showOurServices = list.isNotEmpty()
            ourServicesList.clear()
            ourServicesList = list as ArrayList<OurServicesDTO>
            loadOurServices()
        }
    }

    /*todo set Our Services*/
    private fun loadOurServices() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<OurServicesDTO, OurServicesItemBinding>(
                requireContext(),
                ourServicesList
            ) {
            override val layoutResId: Int
                get() = R.layout.our_services_item

            override fun onBindData(
                model: OurServicesDTO,
                position: Int,
                dataBinding: OurServicesItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: OurServicesDTO, position: Int) {

            }
        }

        binding.rcyOurServices.apply {
            adapter = mAdapter
        }
    }

    /*todo observing shop by brand data from room db*/
    private fun getShopByBrandsData() {

        viewModel.shopByBrandsList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getShopByBrandsData: ${list.size}")
            binding.showShopByBrand = list.isNotEmpty()
            shopByBrandList.clear()
            shopByBrandList = list as ArrayList<ShopByBrandDTO>
            loadShopByBrand()
        }
    }

    /*todo set Shop By Brand*/
    private fun loadShopByBrand() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<ShopByBrandDTO, BrandItemBinding>(
                requireContext(),
                shopByBrandList
            ) {
            override val layoutResId: Int
                get() = R.layout.brand_item

            override fun onBindData(
                model: ShopByBrandDTO,
                position: Int,
                dataBinding: BrandItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: ShopByBrandDTO, position: Int) {

            }
        }

        binding.rcyShopByBrand.apply {
            adapter = mAdapter
        }
    }

    /*todo observing Banner2 data from room db*/
    private fun getBanner2Data() {

        viewModel.banner2List.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getBanner2Data: ${list.size}")
            binding.showBanner2 = list.isNotEmpty()
            banner2List.clear()
            banner2List = list as ArrayList<Banner2DTO>
            loadBanner2()
        }
    }

    /*todo set Banner2*/
    private fun loadBanner2() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<Banner2DTO, Banner2ItemBinding>(
                requireContext(),
                banner2List
            ) {
            override val layoutResId: Int
                get() = R.layout.banner2_item

            override fun onBindData(
                model: Banner2DTO,
                position: Int,
                dataBinding: Banner2ItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: Banner2DTO, position: Int) {

            }
        }

        binding.rcyBanner2.apply {
            adapter = mAdapter
        }
    }

    /*todo observing Top Selling data from room db*/
    private fun getTopSellingData() {

        viewModel.topSellingList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getTopSellingData: ${list.size}")
            binding.showTopSelling = list.isNotEmpty()
            topSellingList.clear()
            topSellingList = list as ArrayList<TopSellingDTO>
            loadTopSelling()
        }
    }

    /*todo set Top Selling*/
    private fun loadTopSelling() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<TopSellingDTO, TopSellingItemBinding>(
                requireContext(),
                topSellingList
            ) {
            override val layoutResId: Int
                get() = R.layout.top_selling_item

            override fun onBindData(
                model: TopSellingDTO,
                position: Int,
                dataBinding: TopSellingItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: TopSellingDTO, position: Int) {

            }
        }

        binding.rcyTopSelling.apply {
            adapter = mAdapter
        }
    }

    /*todo observing Banner2 Full Width data from room db*/
    private fun getBanner2FullWidthData() {

        viewModel.banner2FullWidthList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getBanner2FullWidthData: ${list.size}")
            binding.showBanner2FullWidth = list.isNotEmpty()
            banner2FullWidthList.clear()
            banner2FullWidthList = list as ArrayList<Banner2FullWidthDTO>
            loadBanner2FullWidth()
        }
    }

    /*todo set Banner2 Full Width*/
    private fun loadBanner2FullWidth() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<Banner2FullWidthDTO, Banner2FullwidthItemBinding>(
                requireContext(),
                banner2FullWidthList
            ) {
            override val layoutResId: Int
                get() = R.layout.banner2_fullwidth_item

            override fun onBindData(
                model: Banner2FullWidthDTO,
                position: Int,
                dataBinding: Banner2FullwidthItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: Banner2FullWidthDTO, position: Int) {

            }
        }

        binding.rcyBanner2FullWidth.apply {
            adapter = mAdapter
        }
    }

    /*todo observing Most Viewed data from room db*/
    private fun getMostViewedData() {

        viewModel.mostViewedList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getMostViewedData: ${list.size}")
            binding.showMostViewed = list.isNotEmpty()
            mostViewedList.clear()
            mostViewedList = list as ArrayList<MostViewedDTO>
            loadMostViewed()
        }
    }

    /*todo set Most Viewed*/
    private fun loadMostViewed() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<MostViewedDTO, MostViewedItemBinding>(
                requireContext(),
                mostViewedList
            ) {
            override val layoutResId: Int
                get() = R.layout.most_viewed_item

            override fun onBindData(
                model: MostViewedDTO,
                position: Int,
                dataBinding: MostViewedItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: MostViewedDTO, position: Int) {

            }
        }

        binding.rcyMostViewed.apply {
            adapter = mAdapter
        }
    }

    /*todo observing Bottom Slider data from room db*/
    private fun getBottomSliderData() {

        viewModel.bottomSliderList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getBottomSliderListData: ${list.size}")
            binding.showBottomSlider = list.isNotEmpty()
            bottomSliderList.clear()
            bottomSliderList = list as ArrayList<BottomSliderDTO>
            loadBottomSlider()
        }
    }

    /*todo set bottom slider adapter to load bottom slider*/
    private fun loadBottomSlider() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<BottomSliderDTO, BottomSliderItemBinding>(
                requireContext(),
                bottomSliderList
            ) {
            override val layoutResId: Int
                get() = R.layout.bottom_slider_item

            override fun onBindData(
                model: BottomSliderDTO,
                position: Int,
                dataBinding: BottomSliderItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: BottomSliderDTO, position: Int) {

            }
        }

        binding.bottomSliderViewPager.apply {
            adapter = mAdapter
        }
        binding.dotsIndicatorBottomSlider.setViewPager2(binding.bottomSliderViewPager)
    }

    /*todo observing Chosen For You data from room db*/
    private fun getChosenForYouData() {

        viewModel.chosenForYouList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getChosenForYouData: ${list.size}")
            binding.showChosenForYou = list.isNotEmpty()
            chosenForYouList.clear()
            chosenForYouList = list as ArrayList<ChosenForYouDTO>
            loadChosenForYou()
        }
    }

    /*todo set Chosen For You*/
    private fun loadChosenForYou() {
        val myAdapter = ChoseForYouAdapter(
            requireContext(),
            chosenForYouList
        )
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcyViewChosenForYou.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = myAdapter
        }
    }

    /*todo observing Hot Deals data from room db*/
    private fun getHotDealsData() {

        viewModel.hotDealsList.observe(
            viewLifecycleOwner
        ) { list ->
            Log.d(mTAG, "getHotDealsData: ${list.size}")
            binding.showHotDeals = list.isNotEmpty()
            hotDealsList.clear()
            hotDealsList = list as ArrayList<HotDealsDTO>
            loadHotDeals()
        }
    }

    /*todo set Hot Deals*/
    private fun loadHotDeals() {
        val mAdapter = object :
            CommonRecyclerViewAdapter<HotDealsDTO, HotDealsItemBinding>(
                requireContext(),
                hotDealsList
            ) {
            override val layoutResId: Int
                get() = R.layout.hot_deals_item

            override fun onBindData(
                model: HotDealsDTO,
                position: Int,
                dataBinding: HotDealsItemBinding
            ) {
                dataBinding.model = model
                dataBinding.executePendingBindings()
            }

            override fun onItemClick(model: HotDealsDTO, position: Int) {

            }
        }

        binding.rcyHotDeals.apply {
            adapter = mAdapter
        }
    }

    private fun hideSwipeToRefresh() {
        binding.apply {
            if (swipeToRefresh.isRefreshing) {
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.layoutStaticUi.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
        binding.layoutStaticUi.visibility = View.VISIBLE
        hideSwipeToRefresh()
    }

    private val swipeToRefreshListener = SwipeRefreshLayout.OnRefreshListener {

        /*todo when swipe refresh, all offline data will be deleted and api will call to save new latest data*/
        Toast.makeText(activity, getString(R.string.refreshing_home_page_msg), Toast.LENGTH_SHORT)
            .show()
        isSwipeRefresh = true
        viewModel.clearDB()
        if (checkInternetHelper.isInternetAvailable()) {
            viewModel.getHomeScreenData()
        } else {
            Toast.makeText(
                activity,
                checkInternetHelper.networkErrorMsg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClick(v: View) {
        if (v == binding.txtViewViewAllCategory) {
            val action =
                HomeFragmentDirections.actionHomeFragmentToViewAllCategoryFragment()
            findNavController().navigate(action)
        }
    }
}