package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.BannerImageDTO
import com.example.e_comm_home_demo_app.data.model.FeaturedCategoryDTO
import com.example.e_comm_home_demo_app.data.model.MostViewedDTO
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowMostViewedUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowMostViewedUseCase"

    operator fun invoke(): LiveData<List<MostViewedDTO>> {
        return repository.getMostViewed()
    }
}