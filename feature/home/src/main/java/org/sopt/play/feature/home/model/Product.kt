package org.sopt.play.feature.home.model

import androidx.annotation.DrawableRes

data class Product(
    val productId: Int,
    @param:DrawableRes val thumbnail: Int,
    val storeName: String,
    val productName: String,
    val price: Int,
    val finalPrice: Int
)
