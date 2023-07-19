package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.Banner2DTO
import com.example.e_comm_home_demo_app.data.model.BannerImageDTO
import com.example.e_comm_home_demo_app.data.model.FeaturedCategoryDTO
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowBanner2UseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowBanner2UseCase"

    operator fun invoke(): LiveData<List<Banner2DTO>> {
        return repository.getBanner2()
    }
}