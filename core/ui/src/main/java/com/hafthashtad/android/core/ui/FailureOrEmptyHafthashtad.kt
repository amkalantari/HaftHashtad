package com.hafthashtad.android.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
fun HafthashtadFailure(
    modifier: Modifier = Modifier,
    message: String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Preview(
    name = "FailureOrEmptyHafthashtad",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "FailureOrEmptyHafthashtad",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewFailureOrEmptyHafthashtad() {
    HafthashtadTheme {
        HafthashtadBackground {
            HafthashtadFailure(Modifier, message = "error message")
        }
    }
}
