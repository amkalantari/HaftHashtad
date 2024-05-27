package com.hafthashtad.android.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterial3Api
@Composable
fun HafthashtadBadgeBox(
    boxModifier: Modifier = Modifier,
    boxContent: @Composable BoxScope.() -> Unit,
    badgeModifier: Modifier = Modifier,
    badgeContent: @Composable RowScope.() -> Unit,
) {
    BadgedBox(
        badge = {
            HafthashtadBadge(
                modifier = badgeModifier,
                content = badgeContent
            )
        },
        modifier = boxModifier,
        content = boxContent
    )
}

@ExperimentalMaterial3Api
@Composable
fun HafthashtadBadge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    Badge(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColorFor(containerColor),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "HafthashtadBadgeBox Light Preview",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadBadgeBox Dark Preview",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewHafthashtadBadgeBox() {
    NavigationBar {
        NavigationBarItem(
            icon = {
                HafthashtadBadgeBox(
                    boxModifier = Modifier,
                    boxContent = {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Favorite"
                        )
                    },
                    badgeModifier = Modifier,
                    badgeContent = {
                        val badgeNumber = "8"
                        Text(
                            badgeNumber,
                            modifier = Modifier.semantics {
                                contentDescription = "$badgeNumber new notifications"
                            }
                        )
                    },
                )
            },
            selected = false,
            onClick = {}
        )
    }
}