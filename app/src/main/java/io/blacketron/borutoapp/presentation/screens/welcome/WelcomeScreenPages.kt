package io.blacketron.borutoapp.presentation.screens.welcome

import androidx.annotation.DrawableRes

sealed class WelcomeScreenPages(
    @DrawableRes
    val imageResource: Int,
    val title: String,
    val description: String
){

    class PageContent(imageResource: Int, title: String, description: String): WelcomeScreenPages(
        imageResource = imageResource,
        title = title,
        description = description
    )

    /*object SecondPage: WelcomeScreenPages(
        imageResource = R.drawable.explore,
        title = "Explore",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )*/

    /*object ThirdPage: WelcomeScreenPages(
        imageResource = R.drawable.power,
        title = "Power",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )*/
}