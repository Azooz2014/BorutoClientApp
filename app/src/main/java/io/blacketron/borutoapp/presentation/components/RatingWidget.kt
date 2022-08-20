package io.blacketron.borutoapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.ui.theme.GoldenYellow
import io.blacketron.borutoapp.ui.theme.LightGray

const val FILLED_STARS_KEY = "FILLED_STARS"
const val HALF_FILLED_STARS_KEY = "HALF_FILLED_STARS"
const val EMPTY_STARS_KEY = "EMPTY_STARS"

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 2f
) {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }

    val ratingResult = calculateStars(rating = rating)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ratingResult[FILLED_STARS_KEY]?.let {
            repeat(it){
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor)
            }
        }
        ratingResult[HALF_FILLED_STARS_KEY]?.let {
            repeat(it){
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor)
            }
        }
        ratingResult[EMPTY_STARS_KEY]?.let {
            repeat(it){
                EmptyStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor)
            }
        }

    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        val left = (canvasSize.width / 2f) - (starPathBounds.width / 1.7f)
        val top = (canvasSize.height / 2f) - (starPathBounds.height / 1.7f)

        scale(
            scale = scaleFactor
        ){
            translate(
                top = top,
                left = left
            ) {
                drawPath(
                    path = starPath,
                    color = GoldenYellow
                )
            }
        }


    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        val left = (canvasSize.width / 2f) - (starPathBounds.width / 1.7f)
        val top = (canvasSize.height / 2f) - (starPathBounds.height / 1.7f)

        scale(
            scale = scaleFactor
        ){
            translate(
                top = top,
                left = left
            ) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(0.5f)
                )
                clipPath(
                    path = starPath
                ){
                    drawRect(
                        color = GoldenYellow,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }


    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        val left = (canvasSize.width / 2f) - (starPathBounds.width / 1.7f)
        val top = (canvasSize.height / 2f) - (starPathBounds.height / 1.7f)

        scale(
            scale = scaleFactor
        ){
            translate(
                top = top,
                left = left
            ) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(0.5f)
                )
            }
        }


    }
}

@Composable
fun calculateStars(rating: Double): Map<String, Int> {

    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember{ mutableStateOf(0)}
    var halfFilledStars by remember{ mutableStateOf(0)}
    var emptyStars by remember{ mutableStateOf(0)}

    LaunchedEffect(key1 = rating){

        val (firstNumber, secondNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }

        if(firstNumber in 0..5 && secondNumber in 0..9){
            filledStars = firstNumber
            if (secondNumber in 1..5){
                halfFilledStars++
            }
            if (secondNumber in 6..9){
                filledStars++
            }
            if (firstNumber == 5 && secondNumber > 0){
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
            else{
                Log.d("RatingWidget", "Invalid Rating Number")
            }
        }
    }

    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        FILLED_STARS_KEY to filledStars,
        HALF_FILLED_STARS_KEY to halfFilledStars,
        EMPTY_STARS_KEY to emptyStars
    )

}

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun FilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

@Preview(showBackground = true)
@Composable
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }
    HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

@Preview(showBackground = true)
@Composable
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }
    EmptyStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}