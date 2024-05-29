package com.hafthashtad.android.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme
import com.hafthashtad.android.core.designsystem.theme.robotoFonts

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
