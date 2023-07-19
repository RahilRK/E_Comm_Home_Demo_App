package com.example.e_comm_home_demo_app.domain.usecase

import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import com.example.e_comm_home_demo_app.common.ReponseHandler
import com.example.e_comm_home_demo_app.data.local.MyDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRemoteHomeDataUseCase @Inject constructor(
    private val repository: DomainRepository,
) {
    operator fun invoke(): Flow<ReponseHandler<HomeDataDTO>> = flow {
        try {
            emit(ReponseHandler.Loading())

            val response = repository.getHomeScreenData()
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->

                    emit(ReponseHandler.Success(resultResponse))

                    /*todo save bannerImages*/
                    for (i in resultResponse.bannerImages) {
                        repository.saveBannerImages(i)
                    }

                    /*todo save featuredCategories*/
                    for (i in resultResponse.featuredCategories) {
                        repository.saveCategory(i)
                    }

                    /*todo save our_services*/
                    for (i in resultResponse.carousel[13].banners) {

                        val ourServicesDTO = OurServicesDTO(
                            id = i.id,
                            bannerType = i.bannerType,
                            dominantColor = i.dominantColor,
                            endDate = i.endDate,
                            name = i.name,
                            startDate = i.startDate,
                            title = i.title,
                            url = i.url
                            )

                        repository.saveOurServices(ourServicesDTO)
                    }

                    /*todo save ShopByBrands*/
                    for (i in resultResponse.ShopByBrands) {
                        repository.saveShopByBrands(i)
                    }

                    /*todo save Banners2*/
                    for (i in resultResponse.carousel[4].banners) {

                        val banner2DTO = Banner2DTO(
                            id = i.id,
                            bannerType = i.bannerType,
                            dominantColor = i.dominantColor,
                            endDate = i.endDate,
                            name = i.name,
                            startDate = i.startDate,
                            title = i.title,
                            url = i.url
                        )

                        repository.saveBanner2(banner2DTO)
                    }

                    /*todo save TopSelling & Chosen for you*/
                    for (i in resultResponse.carousel[1].productList) {

                        val topSellingDTO = TopSellingDTO(
                            arType = i.arType,
                            arUrl = i.arUrl,
                            availability = i.dominantColor,
                            dominantColor = i.dominantColor,
                            entityId = i.name,
                            finalPrice = i.finalPrice,
                            formattedFinalPrice = i.formattedFinalPrice,
                            formattedPrice = i.formattedPrice,
                            formattedTierPrice = i.formattedTierPrice,
                            hasRequiredOptions = i.hasRequiredOptions,
                            isAvailable = i.isAvailable,
                            isInRange = i.isInRange,
                            isInWishlist = i.isInWishlist,
                            isNew = i.isNew,
                            isOfferApplicable = i.isOfferApplicable,
                            minAddToCartQty = i.minAddToCartQty,
                            name = i.name,
                            offerlabel = i.offerlabel,
                            price = i.price,
                            rating = i.rating,
                            reviewCount = i.reviewCount,
                            template_baseurl = i.template_baseurl,
                            thumbNail = i.thumbNail,
                            tierPrice = i.tierPrice,
                            typeId = i.typeId,
                            wishlistItemId = i.wishlistItemId,
                        )

                        repository.saveTopSelling(topSellingDTO)
                    }

                    /*todo save Banners2FullWidth*/
                    for (i in resultResponse.carousel[10].banners) {

                        val banner2FullWidthDTO = Banner2FullWidthDTO(
                            id = i.id,
                            bannerType = i.bannerType,
                            dominantColor = i.dominantColor,
                            endDate = i.endDate,
                            name = i.name,
                            startDate = i.startDate,
                            title = i.title,
                            url = i.url
                        )

                        repository.saveBanner2FullWidth(banner2FullWidthDTO)
                    }

                    /*todo save MostViewed*/
                    for (i in resultResponse.carousel[3].productList) {

                        val mostViewedDTO = MostViewedDTO(
                            arType = i.arType,
                            arUrl = i.arUrl,
                            availability = i.dominantColor,
                            dominantColor = i.dominantColor,
                            entityId = i.name,
                            finalPrice = i.finalPrice,
                            formattedFinalPrice = i.formattedFinalPrice,
                            formattedPrice = i.formattedPrice,
                            formattedTierPrice = i.formattedTierPrice,
                            hasRequiredOptions = i.hasRequiredOptions,
                            isAvailable = i.isAvailable,
                            isInRange = i.isInRange,
                            isInWishlist = i.isInWishlist,
                            isNew = i.isNew,
                            isOfferApplicable = i.isOfferApplicable,
                            minAddToCartQty = i.minAddToCartQty,
                            name = i.name,
                            offerlabel = i.offerlabel,
                            price = i.price,
                            rating = i.rating,
                            reviewCount = i.reviewCount,
                            template_baseurl = i.template_baseurl,
                            thumbNail = i.thumbNail,
                            tierPrice = i.tierPrice,
                            typeId = i.typeId,
                            wishlistItemId = i.wishlistItemId,
                        )

                        repository.saveMostViewed(mostViewedDTO)
                    }

                    /*todo save BottomSlider*/
                    for (i in resultResponse.carousel[11].banners) {

                        val bottomSliderDTO = BottomSliderDTO(
                            id = i.id,
                            bannerType = i.bannerType,
                            dominantColor = i.dominantColor,
                            endDate = i.endDate,
                            name = i.name,
                            startDate = i.startDate,
                            title = i.title,
                            url = i.url
                        )

                        repository.saveBottomSlider(bottomSliderDTO)
                    }

                    /*todo save Chosen For You*/
                    for ((pos, i) in resultResponse.carousel[1].productList.withIndex()) {

                        val chosenForYouDTO = ChosenForYouDTO(
                            arType = i.arType,
                            arUrl = i.arUrl,
                            availability = i.dominantColor,
                            dominantColor = i.dominantColor,
                            entityId = i.name,
                            finalPrice = i.finalPrice,
                            formattedFinalPrice = i.formattedFinalPrice,
                            formattedPrice = i.formattedPrice,
                            formattedTierPrice = i.formattedTierPrice,
                            hasRequiredOptions = i.hasRequiredOptions,
                            isAvailable = i.isAvailable,
                            isInRange = i.isInRange,
                            isInWishlist = i.isInWishlist,
                            isNew = i.isNew,
                            isOfferApplicable = i.isOfferApplicable,
                            minAddToCartQty = i.minAddToCartQty,
                            name = i.name,
                            offerlabel = i.offerlabel,
                            price = i.price,
                            rating = i.rating,
                            reviewCount = i.reviewCount,
                            template_baseurl = i.template_baseurl,
                            thumbNail = i.thumbNail,
                            tierPrice = i.tierPrice,
                            typeId = i.typeId,
                            wishlistItemId = i.wishlistItemId,
                            view_type = getViewType(pos+1)
                        )

                        repository.saveChosenForYou(chosenForYouDTO)
                    }

                    /*todo save HotDeals*/
                    for (i in resultResponse.carousel[2].productList) {

                        val hotDealsDTO = HotDealsDTO(
                            arType = i.arType,
                            arUrl = i.arUrl,
                            availability = i.dominantColor,
                            dominantColor = i.dominantColor,
                            entityId = i.name,
                            finalPrice = i.finalPrice,
                            formattedFinalPrice = i.formattedFinalPrice,
                            formattedPrice = i.formattedPrice,
                            formattedTierPrice = i.formattedTierPrice,
                            hasRequiredOptions = i.hasRequiredOptions,
                            isAvailable = i.isAvailable,
                            isInRange = i.isInRange,
                            isInWishlist = i.isInWishlist,
                            isNew = i.isNew,
                            isOfferApplicable = i.isOfferApplicable,
                            minAddToCartQty = i.minAddToCartQty,
                            name = i.name,
                            offerlabel = i.offerlabel,
                            price = i.price,
                            rating = i.rating,
                            reviewCount = i.reviewCount,
                            template_baseurl = i.template_baseurl,
                            thumbNail = i.thumbNail,
                            tierPrice = i.tierPrice,
                            typeId = i.typeId,
                            wishlistItemId = i.wishlistItemId,
                        )

                        repository.saveHotDeals(hotDealsDTO)
                    }
                }
            }

            emit(ReponseHandler.Error(response.message()))
        } catch (e: HttpException) {
            emit(ReponseHandler.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(ReponseHandler.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    private fun getViewType(pos: Int):Int {
        if ((pos % 2) == 0) {
            // number is even
            return 2
        }

        else {
            // number is odd
            return 1
        }
    }
}