package io.blacketron.borutoapp.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.ui.theme.TopAppBarBackgroundColor
import io.blacketron.borutoapp.ui.theme.TopAppBarContentTextColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = TopAppBarBackgroundColor
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    text = "Search...",
                    color = TopAppBarContentTextColor
                )
            },
            textStyle = TextStyle(color = TopAppBarContentTextColor),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_desc),
                        tint = TopAppBarContentTextColor
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { if (text.isNotEmpty()) onTextChange("") else onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.closing_icon_desc),
                        tint = TopAppBarContentTextColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = TopAppBarContentTextColor
            )
        )
//        Log.d("SearchTopBar", "Test Value is: ${text}")
    }
}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(
        text = "",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchTopBarPreviewDark() {
    SearchTopBar(text = "",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {})
}