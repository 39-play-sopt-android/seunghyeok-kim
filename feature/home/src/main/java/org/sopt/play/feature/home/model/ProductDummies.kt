package org.sopt.play.feature.home.model

import android.R.attr.thumbnail
import org.sopt.play.feature.home.R

object ProductDummies {
    val productList = listOf(
        Product(
            thumbnail = R.drawable.product1,
            storeName = "트랜디어패럴",
            productName = "[카프리1위\uD83C\uDFC6/5부,7부,9부기장/MADE] 클립 스판 슬릿 바이커 카프리 데님 팬츠",
            price = 45000,
            finalPrice = 28800
        ),
        Product(
            thumbnail = R.drawable.product2,
            storeName = "슬로우앤드",
            productName = "[\uD83C\uDF41가을/3기장선택!] made. 모먼트 투커버 올데이슬랙스 (+숏/베이직/롱ver) - 4 size",
            price = 53900,
            finalPrice = 26820
        ),
        Product(
            thumbnail = R.drawable.product3,
            storeName = "어텀",
            productName = "보울7부티셔츠",
            price = 19000,
            finalPrice = 17100
        ),
        Product(
            thumbnail = R.drawable.product4,
            storeName = "슬로우앤드",
            productName = "[오픈할인중] made. 도브 클래식 탄탄핏 무지셔츠 - 4 color",
            price = 59000,
            finalPrice = 35200
        )
    )
}