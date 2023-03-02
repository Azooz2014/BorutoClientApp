package io.blacketron.borutoapp.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.blacketron.borutoapp.presentation.common.ListContent
import io.blacketron.borutoapp.ui.theme.statusBarColor

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val query = searchViewModel.searchQuery

    val searchedHeroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    //Set status bar color to default.
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = statusBarColor
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                text = query.value,
                onTextChange = { searchViewModel.updateSearchQuery(query = it) },
                onSearchClicked = { searchViewModel.searchHeroes(query = it) },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(heroes = searchedHeroes, navHostController = navController)
        }
    )

}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(rememberNavController())
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreviewDark() {
    SearchScreen(rememberNavController())
}