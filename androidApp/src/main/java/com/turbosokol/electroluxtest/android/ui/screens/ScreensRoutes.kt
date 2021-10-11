package com.turbosokol.electroluxtest.android.ui.screens



sealed class ScreensRoutes(val route: String) {
    object MainScreen: ScreensRoutes(route = "main_screen")
    object DetailScreen: ScreensRoutes(route = "{url}/detail_screen"){
        fun createRoute(url: String?): String
        {
           val list = url?.toList()
           return "$list/detail_screen"
        }
    }
}