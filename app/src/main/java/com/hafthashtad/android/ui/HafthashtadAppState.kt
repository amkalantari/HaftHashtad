package com.hafthashtad.android.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.hafthashtad.android.core.ui.TrackDisposableJank
import com.hafthashtad.android.feature.home.navigation.navigateToHome
import com.hafthashtad.android.navigation.TopLevelDestination

@Composable
fun rememberHafthashtadAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): HafthashtadAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController, windowSizeClass) {
        HafthashtadAppState(
            navController,
            windowSizeClass,
        )
    }
}

@Stable
class HafthashtadAppState(
    val navController: NavHostController,
    private val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = TopLevelDestination.getTopLevelDestination(currentDestination?.route)

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    fun topLevelDestinations(): List<TopLevelDestination> {
        return TopLevelDestination.entries
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {

                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true

                restoreState = false
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> {
                    navController.navigateToHome(topLevelNavOptions)
                }
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

@Composable
private fun NavigationTrackingSideEffect(
    navController: NavHostController,
) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
