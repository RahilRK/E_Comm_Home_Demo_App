package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.FeaturedCategoryDTO
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowFeatureCategoryUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowFeatureCategoryUseCase"

    operator fun invoke(): LiveData<List<FeaturedCategoryDTO>> {
        return repository.getFeatureCategory()
    }
}