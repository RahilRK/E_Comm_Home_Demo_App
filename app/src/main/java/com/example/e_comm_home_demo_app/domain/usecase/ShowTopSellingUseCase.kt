package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.Banner2DTO
import com.example.e_comm_home_demo_app.data.model.BannerImageDTO
import com.example.e_comm_home_demo_app.data.model.FeaturedCategoryDTO
import com.example.e_comm_home_demo_app.data.model.TopSellingDTO
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowTopSellingUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowTopSellingUseCase"

    operator fun invoke(): LiveData<List<TopSellingDTO>> {
        return repository.getTopSelling()
    }
}