package com.hafthashtad.android.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.hafthashtad.android.core.designsystem.component.HafthashtadNavigationBar
import com.hafthashtad.android.core.designsystem.component.HafthashtadNavigationBarItem
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcon
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.navigation.HafthashtadNavHost
import com.hafthashtad.android.navigation.TopLevelDestination

@Composable
fun HafthashtadApp(
    windowSizeClass: WindowSizeClass,
    appState: HafthashtadAppState = rememberHafthashtadAppState(
        windowSizeClass = windowSizeClass
    ),
    startDestination: String,
) {
    HafthashtadBackground {
        HafthashtadAppContent(
            appState = appState,
            startDestination = startDestination
        )
    }
}

@OptIn(
    ExperimentalComposeUiApi::class,
)
@Composable
private fun HafthashtadAppContent(
    appState: HafthashtadAppState,
    startDestination: String,
) {
    Scaffold(
        modifier = Modifier.semantics { testTagsAsResourceId = true },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    HafthashtadBottomBar(
                        destinations = appState.topLevelDestinations(),
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        },
        topBar = {

        }
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            HafthashtadNavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,
                startDestination = startDestination,
                modifier = Modifier
                    .padding(padding)
                    .safeDrawingPadding()
            )
        }
    }
}

@Composable
private fun HafthashtadBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    HafthashtadNavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            HafthashtadNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    when (destination.unselectedIcon) {
                        is HafthashtadIcon.ImageVectorIcon -> Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = destination.unselectedIcon.imageVector,
                            contentDescription = null
                        )

                        is HafthashtadIcon.DrawableResourceIcon -> Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = destination.unselectedIcon.id),
                            contentDescription = null
                        )
                    }
                },
                selectedIcon = {
                    when (destination.selectedIcon) {
                        is HafthashtadIcon.ImageVectorIcon -> Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = destination.selectedIcon.imageVector,
                            contentDescription = null
                        )

                        is HafthashtadIcon.DrawableResourceIcon -> Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = destination.selectedIcon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        destination.route == it.route
    } ?: false