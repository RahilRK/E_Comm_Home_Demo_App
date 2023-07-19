package com.example.e_comm_home_demo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bannerImages")
data class BannerImageDTO(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var bannerType: String? = "",
    var dominantColor: String? = "",
    var endDate: String? = "",
    var name: String? = "",
    var startDate: String? = "",
    var url: String? = ""
)

/*
fun BannerImageDTO.toDomainBannerImage(): BannerImage {
    return BannerImage(
        id = this.id,
        name = this.name,
        url = this.url,
    )
}*/
