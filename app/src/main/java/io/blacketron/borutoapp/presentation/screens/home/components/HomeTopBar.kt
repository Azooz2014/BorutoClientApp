package io.blacketron.borutoapp.presentation.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.ui.theme.TopAppBarBackgroundColor
import io.blacketron.borutoapp.ui.theme.TopAppBarTextColor

@Composable
fun TopBar(onSearchClicked: () -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_app_bar_text),
                color = TopAppBarTextColor
            )
        },
        backgroundColor = TopAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.accessiblity_search_icon))
            }
        }
    )
}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview
@Composable
fun TopBarPreview() {
    TopBar {

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarDarkPreview() {
    TopBar {

    }
}