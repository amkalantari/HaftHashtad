package com.hafthashtad.android.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.R
import com.hafthashtad.android.core.designsystem.component.HafthashtadFilledButton
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
fun HafthashtadFailure(
    modifier: Modifier = Modifier,
    message: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        Text(
            text = message,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        HafthashtadFilledButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                onRefreshClick()
            },
            text = {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.background
                )
            }
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
fun PreviewHafthashtadFailure() {
    HafthashtadTheme {
        HafthashtadBackground {
            HafthashtadFailure(Modifier, message = "error message") {}
        }
    }
}
