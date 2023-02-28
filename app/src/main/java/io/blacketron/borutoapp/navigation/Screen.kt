package io.blacketron.borutoapp.navigation

import io.blacketron.borutoapp.util.*

sealed class Screen(val route: String) {
    object Splash : Screen(ROUTE_SPLASH_SCREEN)
    object Welcome : Screen(ROUTE_WELCOME_SCREEN)
    object Home : Screen(ROUTE_HOME_SCREEN)
    object Search : Screen(ROUTE_SEARCH_SCREEN)
    object Details : Screen("$ROUTE_DETAILS_SCREEN/{heroId}") {
        fun passHeroId(heroId: Int): String {
            return "${ROUTE_DETAILS_SCREEN}/$heroId"
        }
    }
}
