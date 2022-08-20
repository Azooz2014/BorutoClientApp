package io.blacketron.borutoapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.blacketron.borutoapp.ui.theme.*

@Composable
fun ShimmerEffect(shimmerItemCount: Int) {

    val count = remember {
        if (shimmerItemCount == 0) 2 else shimmerItemCount
    }

    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(
            count = count
        ){
            AnimatedShimmer()
        }
    }
}

@Composable
fun AnimatedShimmer() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))

    ShimmerItemEffect(alphaAnim)
}

@Composable
fun ShimmerItemEffect(alpha: Float) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(ITEM_HEIGHT),
        color = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(ROUNDED_CORNER_SIZE)
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(30.dp)
                    .alpha(alpha),
                color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(5.dp)
            ) {}
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            repeat(times = 3){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .alpha(alpha),
                    color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(5.dp)
                ) {}
                Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(times = 5){
                    Surface(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                            .alpha(alpha),
                        color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(5.dp)
                    ) {}
                    Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
                }
            }

        }
    }

}

@Preview
@Composable
fun ShimmerPreview() {
    ShimmerEffect(1)
}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerDarkPreview() {
    ShimmerEffect(1)
}
