package com.example.e_comm_home_demo_app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "featuredCategories")
data class FeaturedCategoryDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var categoryId: String? = "",
    var categoryName: String? = "",
    var dominantColor: String? = "",
    var url: String = ""
): Parcelable

/*
fun FeaturedCategoryDTO.toDomainFeaturedCategory():FeaturedCategory {
    return FeaturedCategory(
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        url = this.url,
    )
}*/
