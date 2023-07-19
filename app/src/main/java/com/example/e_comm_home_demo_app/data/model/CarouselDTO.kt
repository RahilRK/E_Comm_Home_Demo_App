package com.example.e_comm_home_demo_app.data.model

data class CarouselDTO(
    val banners: List<BannerDTO>,//3,5,7,9
    val id: String,
    val label: String,
    val productList: List<ProductDTO>,//6,8,10,11
    val type: String
)