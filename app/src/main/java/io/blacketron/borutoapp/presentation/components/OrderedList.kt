package io.blacketron.borutoapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.blacketron.borutoapp.ui.theme.EXTRA_SMALL_PADDING

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    titleTextColor: Color,
    itemsTextColor: Color
) {
    Column(verticalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier.padding(bottom = EXTRA_SMALL_PADDING),
            text = title.replaceFirstChar { it.uppercase() },
            color = titleTextColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed { index, string ->
            val item = "${index + 1}. ${string.replaceFirstChar { it.uppercase() }}"
            Text(
                text = item,
                color = itemsTextColor,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderedListPreview() {
    OrderedList(
        title = "title",
        items = listOf("item 1", "item2", "item3"),
        titleTextColor = Color.Black,
        itemsTextColor = Color.Black
    )
}