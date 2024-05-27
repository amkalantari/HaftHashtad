package com.hafthashtad.android.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

/**
 * Hafthashtad filter chip with included leading checked icon as well as text content slot.
 *
 * @param selected Whether the chip is currently checked.
 * @param onSelectedChange Called when the user clicks the chip and toggles checked.
 * @param modifier Modifier to be applied to the chip.
 * @param enabled Controls the enabled state of the chip. When `false`, this chip will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The text label content.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HafthashtadAssistChip(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable () -> Unit
) {
    AssistChip(
        onClick = { onSelectedChange(!selected) },
        label = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                label()
            }
        },
        colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier,
        enabled = enabled,
        trailingIcon = {
            Icon(
                painter = painterResource(id = HafthashtadIcons.Back),
                contentDescription = null
            )
        },
        elevation = AssistChipDefaults.assistChipElevation(elevation = 2.dp)
    )
}

/**
 * Hafthashtad chip default values.
 */
object HafthashtadChipDefaults {
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
    val ChipBorderWidth = 1.dp
}


@Preview(
    name = "HafthashtadFilterChip",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadFilterChip",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadFilterChipPreview() {
    HafthashtadTheme {
        HafthashtadAssistChip(
            selected = true,
            onSelectedChange = {

            },
            modifier = Modifier,
            enabled = true,
            label = {
                Text(text = "amiir")
            }
        )
    }
}
