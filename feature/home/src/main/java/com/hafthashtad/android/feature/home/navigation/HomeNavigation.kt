package com.hafthashtad.android.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hafthashtad.android.feature.home.HomeScreen


const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeRoute,
    ) {
        HomeScreen()
    }
}
