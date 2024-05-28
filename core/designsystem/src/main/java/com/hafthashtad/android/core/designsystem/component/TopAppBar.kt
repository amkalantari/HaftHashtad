package com.hafthashtad.android.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcon
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme
import com.hafthashtad.android.core.designsystem.theme.robotoFonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTitleWithIconsTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: HafthashtadIcon.DrawableResourceIcon,
    notificationCount: Int,
    navigationIconContentDescription: String?,
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title, style = TextStyle(
                    fontFamily = robotoFonts,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    lineHeight = 32.sp
                )
            )
        },
        navigationIcon = {
            Box(contentAlignment = Alignment.Center) {
                if (notificationCount != 0) {
                    HafthashtadBadge(modifier = Modifier.align(Alignment.TopEnd), content = {
                        Text(
                            text = notificationCount.toString(),
                            modifier = Modifier.semantics {
                                contentDescription = "$notificationCount new notifications"
                            }
                        )
                    })
                }
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        painter = painterResource(id = navigationIcon.id),
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = actions,
        colors = colors,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "CenterTitleWithIconsTopAppBar",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "CenterTitleWithIconsTopAppBar",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun CenterTitleWithIconsTopAppBarPreview() {
    HafthashtadTheme {
        Surface {
            CenterTitleWithIconsTopAppBar(
                title = "amiir",
                actions = {

                },
                modifier = Modifier,
                navigationIcon = HafthashtadIcon.DrawableResourceIcon(HafthashtadIcons.Arrow),
                onNavigationClick = {},
                navigationIconContentDescription = "",
                notificationCount = 2
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftTitleTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
) {
    TopAppBar(
        title = {
            Text(
                text = title, style = TextStyle(
                    fontFamily = robotoFonts,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    lineHeight = 32.sp
                )
            )
        },
        modifier = modifier,
        navigationIcon = {},
        actions = actions,
        colors = colors
    )
}

/**
 * Top app bar with action, displayed on the right
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HafthashtadTopAppBarWithLeftTitle(
    title: String,
    navigationIcon: HafthashtadIcon.DrawableResourceIcon,
    navigationIconContentDescription: String?,
    actionIcon: HafthashtadIcon.DrawableResourceIcon?,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, modifier = Modifier.fillMaxWidth()) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    painter = painterResource(id = navigationIcon.id),
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            actionIcon?.let {
                onActionClick?.let {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            painter = painterResource(id = actionIcon.id),
                            contentDescription = actionIconContentDescription,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                } ?: kotlin.run {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        painter = painterResource(id = actionIcon.id),
                        contentDescription = actionIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            } ?: run {
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        colors = colors,
        modifier = modifier
    )
}

/**
 * Top app bar with action, displayed on the right
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithLeftTitle(title: String) {
    TopAppBar(title = { Text(text = title, modifier = Modifier.fillMaxWidth()) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Top App Bar",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Top App Bar",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadTopAppBarWithTitlePreview() {
    HafthashtadTheme {
        Surface {
            HafthashtadTopAppBarWithLeftTitle(
                title = "amiir",
                actionIcon = HafthashtadIcon.DrawableResourceIcon(HafthashtadIcons.Arrow),
                actionIconContentDescription = "Action icon",
                navigationIcon = HafthashtadIcon.DrawableResourceIcon(HafthashtadIcons.Arrow),
                navigationIconContentDescription = "Menu"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "HafthashtadTopAppBar",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadTopAppBar",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadTopAppBarPreview() {
    HafthashtadTheme {
        Surface {
            LeftTitleTopAppBar(
                title = "amiir",
                actions = {

                },
                modifier = Modifier
            )
        }
    }
}
