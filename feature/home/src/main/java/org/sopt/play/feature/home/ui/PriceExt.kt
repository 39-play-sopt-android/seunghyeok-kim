package org.sopt.play.feature.home.ui

import java.text.DecimalFormat
import kotlin.math.roundToInt

//할인가를 넣으면 할인율을 제공해주는 함수
//고민: Int를 쓸까요 Long을 쓸까요... 21억 넘지도 않을거같긴하지만 다른 나라 화폐 단위를 생각하면 Long인거같기도하고요
fun Int.calculateDiscountRate(finalPrice: Int): Int {
    if (this <= 0 || finalPrice >= this) return 0
    if (finalPrice <= 0) return 100

    val rate = ((this - finalPrice).toDouble() / this) * 100
    return rate.roundToInt()
}

private val formatter = DecimalFormat("#,###")

fun Int.toDecimalFormat(): String {
    return formatter.format(this)
}