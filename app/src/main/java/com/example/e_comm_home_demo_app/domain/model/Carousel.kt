package com.example.e_comm_home_demo_app.domain.model

data class Carousel(
    val banners: List<Banner>,//3,5,7,9
    val id: String,
    val label: String,
    val productList: List<Product>,//6,8,10,11
    val type: String
)