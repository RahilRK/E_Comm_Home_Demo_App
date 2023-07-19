package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowBottomSliderUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowBottomSliderUseCase"

    operator fun invoke(): LiveData<List<BottomSliderDTO>> {
        return repository.getBottomSlider()
    }
}