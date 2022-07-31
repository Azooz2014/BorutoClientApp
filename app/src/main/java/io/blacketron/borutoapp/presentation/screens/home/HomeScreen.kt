package io.blacketron.borutoapp.presentation.screens.home

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import io.blacketron.borutoapp.presentation.screens.home.components.TopBar

@Composable
fun HomeScreen(
    /*navHostController: NavHostController*/
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopBar {
                Toast.makeText(context, "Search Clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    ) {

    }
}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}