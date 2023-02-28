package io.blacketron.borutoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import io.blacketron.borutoapp.presentation.screens.details.DetailsScreen
import io.blacketron.borutoapp.presentation.screens.home.HomeScreen
import io.blacketron.borutoapp.presentation.screens.search.SearchScreen
import io.blacketron.borutoapp.presentation.screens.splash.SplashScreen
import io.blacketron.borutoapp.presentation.screens.welcome.WelcomeScreen
import io.blacketron.borutoapp.util.DETAILS_SCREEN_ARGUMENT_KEY

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            //Composable screens go here.
            SplashScreen(navHostController = navHostController)
        }
        composable(route = Screen.Welcome.route) {
            //Composable screens go here.
            WelcomeScreen(navHostController = navHostController)
        }
        composable(route = Screen.Home.route) {
            //Composable screens go here.
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Screen.Search.route) {
            //Composable screens go here.
            SearchScreen(navController = navHostController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_SCREEN_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            //Composable screens go here.
            DetailsScreen(navHostController = navHostController)
        }
    }
}