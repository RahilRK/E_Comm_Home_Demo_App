package com.example.e_comm_home_demo_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.e_comm_home_demo_app.data.model.*
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import javax.inject.Inject

class ShowHotDealsUseCase @Inject constructor(
    private val repository: DomainRepository
) {
    var TAG = "ShowHotDealsUseCase"

    operator fun invoke(): LiveData<List<HotDealsDTO>> {
        return repository.getHotDeals()
    }
}