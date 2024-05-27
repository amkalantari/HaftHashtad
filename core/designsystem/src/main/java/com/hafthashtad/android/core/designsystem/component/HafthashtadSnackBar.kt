package com.hafthashtad.android.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HafthashtadSnackBar(
    modifier: Modifier = Modifier,
    type: SnackBarType = SnackBarType.INFO,
    properties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = false,
        dismissOnBackPress = type != SnackBarType.PERMANENT,
        dismissOnClickOutside = type != SnackBarType.PERMANENT
    ),
    title: String,
    message: String,
    positiveTitle: String,
    negativeTitle: String?,
    onBackedPressed: () -> Unit,
    onNegativeClick: (() -> Unit)? = null,
    onPositiveClick: (() -> Unit)
) {
    val containerColor = getDialogColor(type)
    Dialog(
        properties = properties,
        onDismissRequest = onBackedPressed
    ) {
        Card(
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = containerColor),
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 46.dp)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    horizontalArrangement = if (negativeTitle == null) Arrangement.End else Arrangement.SpaceEvenly
                ) {
                    negativeTitle?.let {
                        HafthashtadTextButton(onClick = { onNegativeClick?.invoke() }) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    HafthashtadTextButton(onClick = onPositiveClick) {
                        Text(
                            text = positiveTitle,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun getDialogColor(type: SnackBarType): Color {
    return when (type) {
        SnackBarType.WARNING, SnackBarType.ATTENTION, SnackBarType.PERMANENT -> MaterialTheme.colorScheme.error
        SnackBarType.INFO, SnackBarType.CONFIRMATION -> MaterialTheme.colorScheme.tertiary
        SnackBarType.SUCCESS -> MaterialTheme.colorScheme.primary
    }
}

@Preview("Hafthashtad SnackBar")
@Composable
fun PreviewSnackBar() {
    HafthashtadSnackBar(
        type = SnackBarType.INFO,
        title = "Sample Title",
        message = "This is a sample message",
        positiveTitle = "OK",
        negativeTitle = "Cancel",
        onBackedPressed = { },
        onPositiveClick = { },
        onNegativeClick = { }
    )
}

enum class SnackBarType {
    INFO,
    CONFIRMATION,
    WARNING,
    ATTENTION,
    SUCCESS,
    PERMANENT
}

fun Modifier.topAlignDialog() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(constraints.maxWidth, constraints.maxHeight) {
        placeable.place(0, -(30.dp).roundToPx(), 10f)
    }
}
