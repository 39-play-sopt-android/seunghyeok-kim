package org.sopt.play.core.navigation

enum class MainTab(
    val route: Route,
    val label: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int
) {
    Home(Route.Home, "홈", R.drawable.ic_home_fill, R.drawable.ic_home),
    Like(Route.Like, "찜", R.drawable.ic_favorite_fill, R.drawable.ic_favorite),
    MyPage(Route.Mypage, "마이페이지", R.drawable.ic_user_fill, R.drawable.ic_user)
}