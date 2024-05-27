package com.hafthashtad.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.hafthashtad.android.feature.home.navigation.homeScreen

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.ai/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun HafthashtadNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        homeScreen()
    }
}

fun popUpToTop(navController: NavController) = navOptions {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return@navOptions) {
        inclusive = true
    }
}
