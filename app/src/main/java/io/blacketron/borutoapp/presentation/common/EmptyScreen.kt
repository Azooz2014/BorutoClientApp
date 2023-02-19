package io.blacketron.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.ui.theme.ErrorIconColor
import io.blacketron.borutoapp.ui.theme.ErrorTitleColor
import io.blacketron.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {

    var message by remember { mutableStateOf("Find Your Favorite Hero!") }
    var icon by remember {
        mutableStateOf(R.drawable.search_document)
    }
    var startAnimation by remember { mutableStateOf(false) }
    val animAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 0.75f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    if (error != null) {
        message = parseMessage(error)
        icon = R.drawable.network_error
    }

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        message = message,
        icon = icon,
        alpha = animAlpha,
        error = error,
        heroes = heroes
    )
}

@Composable
fun EmptyContent(
    message: String,
    icon: Int,
    alpha: Float,
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {

    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        swipeEnabled = error != null,
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_ic_desc),
                tint = ErrorIconColor
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha),
                text = message,
                color = ErrorTitleColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}

fun parseMessage(state: LoadState.Error): String {

    return when (state.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "No Connection Available"
        }
        else -> "Unknown Error"
    }
}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenDarkPreview() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}
