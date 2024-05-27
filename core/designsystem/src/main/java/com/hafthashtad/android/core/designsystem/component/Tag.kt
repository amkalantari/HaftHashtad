package com.hafthashtad.android.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hafthashtad.android.core.designsystem.R

@Composable
fun HafthashtadTopicTag(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    followed: Boolean,
    onDropMenuToggle: (show: Boolean) -> Unit = {},
    onFollowClick: () -> Unit,
    onUnfollowClick: () -> Unit,
    onBrowseClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    followText: @Composable () -> Unit = { Text(stringResource(R.string.app_name)) },
    unFollowText: @Composable () -> Unit = { Text(stringResource(R.string.app_name)) },
    browseText: @Composable () -> Unit = { Text(stringResource(R.string.app_name)) }
) {

    Box(modifier = modifier) {
        val containerColor = if (followed) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
        HafthashtadTextButton(
            onClick = { onDropMenuToggle(true) },
            enabled = enabled,
            small = true,
            colors = HafthashtadButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
                disabledContainerColor = if (followed) {
                    MaterialTheme.colorScheme.onBackground.copy(
                        alpha = HafthashtadButtonDefaults.DisabledButtonContentAlpha
                    )
                } else {
                    Color.Transparent
                }
            ),
            text = text
        )
        HafthashtadDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDropMenuToggle(false) },
            items = if (followed) listOf(UNFOLLOW, BROWSE) else listOf(FOLLOW, BROWSE),
            onItemClick = { item ->
                when (item) {
                    FOLLOW -> onFollowClick()
                    UNFOLLOW -> onUnfollowClick()
                    BROWSE -> onBrowseClick()
                }
            },
            itemText = { item ->
                when (item) {
                    FOLLOW -> followText()
                    UNFOLLOW -> unFollowText()
                    BROWSE -> browseText()
                }
            }
        )
    }
}

private const val FOLLOW = 1
private const val UNFOLLOW = 2
private const val BROWSE = 3

@Preview("Topic Tag")
@Composable
fun HafthashtadTopicTagPreview() {
    HafthashtadTopicTag(
        expanded = true,
        followed = false,
        onDropMenuToggle = {},
        onFollowClick = { },
        onUnfollowClick = { },
        onBrowseClick = {},
        text = { Text(text = "Topic 1".uppercase()) },
        followText = { Text(text = "Follow") },
        unFollowText = { Text(text = "Unfollow") },
        browseText = { Text(text = "Browse topic") }
    )
}
