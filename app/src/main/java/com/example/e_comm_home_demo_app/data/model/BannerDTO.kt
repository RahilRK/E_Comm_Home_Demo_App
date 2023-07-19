package com.example.e_comm_home_demo_app.data.model

data class BannerDTO(
    var bannerType: String,
    var dominantColor: String,
    var endDate: String? = "",
    var id: String,
    var name: String,
    var startDate: String,
    var title: String,
    var url: String
)

/*
fun BannerDTO.toDomainBanner(): Banner {
    return Banner(
        title = this.title,
        url = this.url,
    )
}*/
