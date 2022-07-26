package io.blacketron.borutoapp.presentation.screens.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.*
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.ui.theme.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    welcomeViewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    val pages = listOf(
        WelcomeScreenPages.PageContent(
            imageResource = R.drawable.greetings,
            title = stringResource(id = R.string.greeting_page_title),
            description = stringResource(R.string.greetings_page_description)
        ),
        WelcomeScreenPages.PageContent(
            imageResource = R.drawable.explore,
            title = stringResource(R.string.explore_page_title),
            description = stringResource(R.string.explore_page_description)
        ),
        WelcomeScreenPages.PageContent(
            imageResource = R.drawable.power,
            title = stringResource(R.string.power_page_title),
            description = stringResource(R.string.power_page_description)
        )
    )

    val pagerState = rememberPagerState()

    val isViewModelFinished by welcomeViewModel.isFinished.collectAsState()

    //For Debugging purposes only, to be removed later.
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val TAG = "WelcomeScreen"



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = pages.size,
            state = pagerState
        ) { page ->
            Page(welcomeScreenPage = pages[page])
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = activeIndicatorColor,
            inactiveColor = inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )

        FinishButton(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState,
            lastPage = pages.size - 1
        ) {
            welcomeViewModel.welcomePageCompleted(isCompleted = true)
        }
    }

    LaunchedEffect(isViewModelFinished){
        Log.d(TAG, "LaunchedEffect triggered!")
        if (isViewModelFinished){
            Log.d(TAG, "current navigation before popBackStack call : ${navBackStackEntry?.destination.toString()}")
            navHostController.popBackStack()
            Log.d(TAG, "current navigation after popBackStack call : ${navBackStackEntry?.destination.toString()}")
        }
    }
}

@Composable
fun Page(welcomeScreenPage: WelcomeScreenPages) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = welcomeScreenBackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = welcomeScreenPage.imageResource),
            contentDescription = stringResource(R.string.page_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING),
            text = welcomeScreenPage.title,
            textAlign = TextAlign.Center,
            color = welcomeScreenTitleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = welcomeScreenPage.description,
            textAlign = TextAlign.Center,
            color = welcomeScreenDescriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    lastPage: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = modifier.fillMaxWidth(),
            visible = pagerState.currentPage == lastPage
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults
                    .buttonColors(
                        backgroundColor = finishButtonBackgroundColor,
                        contentColor = Color.White
                    )

            ) {
                Text(text = stringResource(R.string.finish_btn_text))
            }
        }

    }
}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun WelcomePage1Preview() {
    val mockPage = WelcomeScreenPages.PageContent(
        imageResource = R.drawable.greetings,
        title = stringResource(id = R.string.greeting_page_title),
        description = stringResource(R.string.greetings_page_description)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Page(welcomeScreenPage = mockPage)
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun WelcomePage1DarkPreview() {
    val mockPage = WelcomeScreenPages.PageContent(
        imageResource = R.drawable.greetings,
        title = stringResource(id = R.string.greeting_page_title),
        description = stringResource(R.string.greetings_page_description)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Page(welcomeScreenPage = mockPage)
    }
}