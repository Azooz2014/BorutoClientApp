package io.blacketron.borutoapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.blacketron.borutoapp.R
import io.blacketron.borutoapp.navigation.Screen
import io.blacketron.borutoapp.ui.theme.splashScreenBackgroundColor

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    splashViewModel: SplashScreenViewModel = hiltViewModel()
    ) {

    val isWelcomeScreenCompleted by splashViewModel.isWelcomePageCompleted.collectAsState()

    val rotationDegrees = remember{Animatable(initialValue = 0f)}

    LaunchedEffect(key1 = true){
        rotationDegrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )

        navHostController.popBackStack()
        if (isWelcomeScreenCompleted){
            navHostController.navigate(Screen.Home.route)
        } else{
            navHostController.navigate(Screen.Welcome.route)
        }
    }

    Splash(rotationDegrees.value)
}

@Composable
fun Splash(rotationDegrees: Float) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(splashScreenBackgroundColor)
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.rotate(rotationDegrees),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

/*@Composable
fun animatedLogoRotation(): Float{

    val rotationDegrees = remember{Animatable(initialValue = 0f)}
    
    LaunchedEffect(key1 = true){
        rotationDegrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 500
            )
        )
    }

    return rotationDegrees.value
}*/


//////////////////////////////////////////////
// Previews
//////////////////////////////////////////////

@Preview
@Composable
fun SplashScreenLightPreview() {
    Splash(0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(0f)
}