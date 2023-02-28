package io.blacketron.borutoapp.presentation.components



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.ui.theme.SMALL_PADDING

@Composable
fun InfoBox(
    icon: Painter,
    title: String,
    desc: String,
    textColor: Color,
    iconColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(36.dp),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )
        Column() {
            Text(
                text = title.replaceFirstChar { it.uppercase() },
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                text = desc.replaceFirstChar { it.uppercase() },
                color = textColor,
                fontSize = MaterialTheme.typography.subtitle2.fontSize
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.bolt),
        title = "98",
        desc = "power",
        textColor = Color.Black,
        iconColor = Color.Black
    )
}