package com.hafthashtad.android.navigation

import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcon
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.feature.home.navigation.homeRoute

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composable.
 */
enum class TopLevelDestination(
    val route: String,
    val selectedIcon: HafthashtadIcon,
    val unselectedIcon: HafthashtadIcon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        route = homeRoute,
        selectedIcon = HafthashtadIcon.DrawableResourceIcon(HafthashtadIcons.Add),
        unselectedIcon = HafthashtadIcon.DrawableResourceIcon(HafthashtadIcons.Add),
        iconTextId = com.hafthashtad.android.core.designsystem.R.string.home,
        titleTextId = com.hafthashtad.android.core.designsystem.R.string.home
    )
    ;

    companion object {
        fun getTopLevelDestination(route: String?) = entries.firstOrNull {
            it.route == route
        }
    }
}
