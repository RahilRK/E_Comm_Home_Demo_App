package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.BannerImageDTO
import com.example.e_comm_home_demo_app.data.model.FeaturedCategoryDTO
import com.example.e_comm_home_demo_app.data.model.ShopByBrandDTO
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowShopByBrandsUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowShopByBrandsUseCase"

    operator fun invoke(): LiveData<List<ShopByBrandDTO>> {
        return repository.getShopByBrand()
    }
}