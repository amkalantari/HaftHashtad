package com.hafthashtad.android.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.R
import com.hafthashtad.android.core.designsystem.component.HafthashtadOutlinedButton
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
fun FailureOrEmptyHafthashtad(
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
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        HafthashtadOutlinedButton(
            onClick = {
                onRefreshClick.invoke()
            },
            text = {
                Text(
                    text = stringResource(id = R.string.refresh),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
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
fun PreviewFailureOrEmptyHafthashtad() {
    HafthashtadTheme {
        HafthashtadBackground {
            FailureOrEmptyHafthashtad(Modifier, message = "error message") {}
        }
    }
}
