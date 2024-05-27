package com.hafthashtad.android.core.ui.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.DarkThemeConfig
import com.hafthashtad.android.core.designsystem.R
import com.hafthashtad.android.core.designsystem.component.HafthashtadDialog
import com.hafthashtad.android.core.designsystem.component.noRippleClickable
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme


@Composable
fun ChangeThemeDialog(
    currentSelected: DarkThemeConfig,
    onClick: ((DarkThemeConfig) -> Unit),
) {
    HafthashtadDialog(
        title = stringResource(id = R.string.theme),
        positiveTitle = stringResource(id = R.string.cancel),
        onPositiveClick = {
            onClick.invoke(currentSelected)
        },
        onDismiss = {},
        contents = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(DarkThemeConfig.values()) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentSelected == item,
                                onClick = {
                                    onClick.invoke(item)
                                },
                                enabled = true,
                            )
                            Text(
                                text = stringResource(id = item.title),
                                modifier = Modifier.fillMaxWidth().noRippleClickable {
                                    onClick.invoke(item)
                                }
                            )
                        }
                    }
                }
            }

        },
        noContentPadding = true
    )
}

@Preview(
    name = "ChangeThemeDialog",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "ChangeThemeDialog",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ChangeThemeDialogPreview() {
    HafthashtadTheme {
        Surface {
            ChangeThemeDialog(currentSelected = DarkThemeConfig.DARK, onClick = {})
        }
    }
}