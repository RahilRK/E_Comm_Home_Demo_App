package com.example.e_comm_home_demo_app.common

import com.example.e_comm_home_demo_app.data.model.HomeDataDTO

data class DataStateHandler(
    val isLoading: Boolean = false,
    val homeDataDTO: HomeDataDTO? = null,
    val error: String = ""
)
