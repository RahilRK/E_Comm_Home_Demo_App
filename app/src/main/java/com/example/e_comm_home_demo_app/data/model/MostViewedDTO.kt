package com.example.e_comm_home_demo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "most_viewed")
data class MostViewedDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
//    var LabelCollection: List<Any>,
//    var arTextureImages: List<Any>,
    var arType: String? = "",
    var arUrl: String? = "",
    var availability: String? = "",
//    var configurableData: ConfigurableData,
    var dominantColor: String? = "",
    var entityId: String? = "",
    var finalPrice: Double,
    var formattedFinalPrice: String? = "",
    var formattedPrice: String? = "",
    var formattedTierPrice: String? = "",
    var hasRequiredOptions: Boolean,
    var isAvailable: Boolean,
    var isInRange: Boolean,
    var isInWishlist: Boolean,
    var isNew: Boolean,
    var isOfferApplicable: Boolean,
    var minAddToCartQty: Int,
    var name: String? = "",
    var offerlabel: String? = "",
    var price: Double,
    var rating: Int,
    var reviewCount: Int,
    var template_baseurl: String? = "",
    var thumbNail: String? = "",
    var tierPrice: String? = "",
    var typeId: String? = "",
    var wishlistItemId: Int
)

/*
fun ProductDTO.toDomainProduct(): Product {
    return Product(
        availability = this.availability,
        entityId = this.entityId,
        finalPrice = this.finalPrice,
        formattedFinalPrice = this.formattedFinalPrice,
        formattedPrice = this.formattedPrice,
        formattedTierPrice = this.formattedTierPrice,
        hasRequiredOptions = this.hasRequiredOptions,
        isAvailable = this.isAvailable,
        isInRange = this.isInRange,
        isInWishlist = this.isInWishlist,
        isNew = this.isNew,
        isOfferApplicable = this.isOfferApplicable,
        name = this.name,
        offerlabel = this.offerlabel,
        price = this.price,
        rating = this.rating,
        reviewCount = this.reviewCount,
        template_baseurl = this.template_baseurl,
        thumbNail = this.thumbNail,
        tierPrice = this.tierPrice,
        typeId = this.typeId,
        wishlistItemId = this.wishlistItemId,
    )
}*/
