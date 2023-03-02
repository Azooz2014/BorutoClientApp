package io.blacketron.borutoapp.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.presentation.components.InfoBox
import io.blacketron.borutoapp.presentation.components.OrderedList
import io.blacketron.borutoapp.ui.theme.*
import io.blacketron.borutoapp.util.BASE_URL

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedHero: Hero?,
    colorPalette: Map<String, String>
) {

    var vibrant by remember {
        mutableStateOf("#000000")
    }
    var darkVibrant by remember {
        mutableStateOf("#000000")
    }
    var onDarkVibrant by remember {
        mutableStateOf("#ffffff")
    }

    //Generate new color palette swatch from hero image when selectedHero is updated.
    LaunchedEffect(key1 = selectedHero) {
        vibrant = colorPalette["vibrant"]!!
        darkVibrant = colorPalette["darkVibrant"]!!
        onDarkVibrant = colorPalette["onDarkVibrant"]!!
    }

    //Update action bar color to reflect the new color generated from the palette.
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val animatedRadius by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) 40.dp else 0.dp
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetShape = RoundedCornerShape(
            topStart = animatedRadius,
            topEnd = animatedRadius
        ),
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    contentColor = Color(parseColor(onDarkVibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    iconColor = Color(parseColor(vibrant))
                )
            }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImgUrl = hero.imageUrl,
                    imgFraction = currentSheetFraction,
                    onCloseClicked = { navHostController.popBackStack() }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    contentColor: Color = ContentColor,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    iconColor: Color = MaterialTheme.colors.primary
) {
    Column(
        Modifier
            .background(sheetBackgroundColor)
            .padding(LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Medium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.bolt),
                title = "${selectedHero.power}",
                desc = stringResource(R.string.info_box_desc_power),
                textColor = contentColor,
                iconColor = iconColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.calendar),
                title = selectedHero.birthMonth,
                desc = stringResource(R.string.info_box_desc_birth_month),
                textColor = contentColor,
                iconColor = iconColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.cake),
                title = selectedHero.birthDay,
                desc = stringResource(R.string.info_box_desc_birth_day),
                textColor = contentColor,
                iconColor = iconColor
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = EXTRA_SMALL_PADDING),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                titleTextColor = contentColor,
                itemsTextColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                titleTextColor = contentColor,
                itemsTextColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                titleTextColor = contentColor,
                itemsTextColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    heroImgUrl: String,
    imgFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${heroImgUrl}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }
    val minimumHeight: Float = 0.4f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imgFraction + minimumHeight)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.accessibility_hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.closing_icon_desc),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Naruto",
            imageUrl = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 4.5,
            power = 0,
            birthMonth = "Oct",
            birthDay = "1st",
            family = listOf("Minato", "Kushina", "Boruto", "Himawari"),
            abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
            natureTypes = listOf("Earth", "Wind")
        )
    )
}