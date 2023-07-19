package com.example.e_comm_home_demo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_by_brands")
data class ShopByBrandDTO(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var brand_image: String? = "",
    var brand_label: String? ="",
    var position: Int,
    var product_count: Int,
    var url: String? = ""
)

/*
fun ShopByBrandDTO.toDomainShopByBrand(): ShopByBrand {
    return ShopByBrand(
        brand_image = this.brand_image,
        brand_label = this.brand_label,
        id = this.id,
        url = this.url,
    )
}*/
