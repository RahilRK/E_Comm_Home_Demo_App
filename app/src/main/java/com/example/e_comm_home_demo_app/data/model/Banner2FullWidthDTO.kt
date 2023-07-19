package com.example.e_comm_home_demo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banner2FullWidth")
data class Banner2FullWidthDTO(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var bannerType: String? = "",
    var dominantColor: String? = "",
    var endDate: String? = "",
    var name: String? = "",
    var startDate: String? = "",
    var title: String? = "",
    var url: String? = ""
)

/*
fun BannerDTO.toDomainBanner(): Banner {
    return Banner(
        title = this.title,
        url = this.url,
    )
}*/
