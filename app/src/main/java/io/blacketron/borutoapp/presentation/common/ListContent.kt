package io.blacketron.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.navigation.Screen
import io.blacketron.borutoapp.presentation.components.RatingWidget
import io.blacketron.borutoapp.presentation.components.ShimmerEffect
import io.blacketron.borutoapp.ui.theme.ITEM_HEIGHT
import io.blacketron.borutoapp.ui.theme.LARGE_PADDING
import io.blacketron.borutoapp.ui.theme.ROUNDED_CORNER_SIZE
import io.blacketron.borutoapp.ui.theme.SMALL_PADDING
import io.blacketron.borutoapp.util.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(heroes = heroes)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ) { heroes ->
                heroes?.let { hero ->
                    HeroItem(hero = hero, navHostController = navHostController)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero: Hero,
    navHostController: NavHostController
) {

    val painter = rememberImagePainter(
        data = "$BASE_URL${hero.imageUrl}"
    ) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .height(ITEM_HEIGHT)
            .clickable { navHostController.navigate(route = Screen.Details.passHeroId(hero.id)) },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(ROUNDED_CORNER_SIZE)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.accessibility_hero_image),
                contentScale = ContentScale.Crop
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            color = Color.Black.copy(0.5f),
            shape = RoundedCornerShape(
                bottomStart = ROUNDED_CORNER_SIZE,
                bottomEnd = ROUNDED_CORNER_SIZE
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = LARGE_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(0.5f),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        color = Color.White.copy(0.5f),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}

@Composable
fun handlePagingResult(heroes: LazyPagingItems<Hero>): Boolean {

    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect(shimmerItemCount = heroes.itemCount)
                false
            }

            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }

            error != null -> {

                EmptyScreen(error = error, heroes = heroes)
                Log.d("ListContent", "handlePagingResult: $error")
                false
            }
            else -> true
        }
    }

}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@ExperimentalCoilApi
@Composable
@Preview
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasuke",
            imageUrl = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            power = 100,
            birthMonth = "",
            birthDay = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        navHostController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HeroItemDarkPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasuke",
            imageUrl = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            power = 100,
            birthMonth = "",
            birthDay = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        navHostController = rememberNavController()
    )
}