package com.hafthashtad.android.core.designsystem.component

import android.content.res.Configuration
import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
fun HafthashtadDialog(
    properties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = true,
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    ),
    title: String? = null,
    titleTextAlign: Alignment.Horizontal = Alignment.Start,
    body: String? = null,
    bodyTextAlign: TextAlign = TextAlign.Start,
    @RawRes jsonRes: Int? = null,
    jsonResSize: Dp? = null,
    noContentPadding: Boolean = false,
    contents: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
    positiveTitle: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    negativeTitle: String? = null,
    onNegativeClick: (() -> Unit)? = null,
) {
    Dialog(
        properties = properties,
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(
                modifier = Modifier.padding(if (noContentPadding) 0.dp else 24.dp)
            ) {
                jsonRes?.let {
                    HafthashtadAnimation(
                        jsonRes = jsonRes,
                        modifier = Modifier
                            .size(jsonResSize ?: 80.dp)
                            .align(titleTextAlign)
                    )
                }
                title?.let {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(all = if (noContentPadding) 24.dp else 0.dp)
                            .align(titleTextAlign)
                    )
                }
                body?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = body,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = bodyTextAlign
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                contents?.let { it() }
                if (positiveTitle != null || negativeTitle != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 24.dp,
                                end = if (noContentPadding) 24.dp else 0.dp,
                                start = if (noContentPadding) 24.dp else 0.dp,
                                bottom = if (noContentPadding) 24.dp else 0.dp,
                            ),
                        horizontalArrangement = negativeTitle?.let { Arrangement.End }
                            ?: kotlin.run {
                                Arrangement.Center
                            }
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
                        positiveTitle?.let {
                            HafthashtadFilledButton(
                                modifier = if (negativeTitle == null) {
                                    Modifier.fillMaxWidth()
                                } else {
                                    Modifier
                                },
                                onClick = {
                                    onPositiveClick?.invoke()
                                }
                            ) {
                                Text(
                                    text = positiveTitle,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HafthashtadAlertDialog(
    onDismiss: () -> Unit,
    title: String,
    body: String,
    positiveTitle: String,
    onPositiveClick: (() -> Unit),
    negativeTitle: String? = null,
    onNegativeClick: (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
        },
        text = {
            Text(text = body, style = MaterialTheme.typography.bodyMedium)
        },
        confirmButton = {
            TextButton(
                onClick = onPositiveClick
            ) {
                Text(text = positiveTitle, style = MaterialTheme.typography.labelLarge)
            }
        },
        dismissButton = negativeTitle?.let {
            {
                TextButton(
                    onClick = { onNegativeClick?.invoke() }
                ) {
                    Text(negativeTitle, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    )
}

@Composable
fun HafthashtadErrorDialog(
    onDismiss: () -> Unit,
    title: String,
    body: String,
    positiveTitle: String,
    onPositiveClick: (() -> Unit),
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title, style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = body, style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            HafthashtadFilledButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onPositiveClick,
                text = {
                    Text(text = positiveTitle, style = MaterialTheme.typography.labelLarge)

                }) {

            }
        }
    )
}

@Composable
fun HafthashtadAlertDialogWithIcon(
    onDismiss: () -> Unit,
    title: String,
    body: String,
    icon: Int? = null,
    positiveTitle: String,
    onPositiveClick: (() -> Unit),
    negativeTitle: String? = null,
    onNegativeClick: (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = icon?.let {
            {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
            }
        },
        title = {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
        },
        text = {
            Text(
                text = body, style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            HafthashtadFilledButton(
                modifier = if (negativeTitle == null) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                },
                onClick = onPositiveClick
            ) {
                Text(
                    text = positiveTitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        dismissButton = negativeTitle?.let {
            {
                TextButton(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    onClick = { onNegativeClick?.invoke() }
                ) {
                    Text(text = negativeTitle, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    )
}


@Preview(
    name = "HafthashtadDialog",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadDialog",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadDialogPreview() {
    HafthashtadTheme {
        Surface {
            HafthashtadDialog(
                title = "title",
                body = "body",
                onDismiss = {},
                positiveTitle = "String",
                negativeTitle = "cancel",
                onPositiveClick = {},
                contents = {
                    Column {
                        Icon(
                            painter = painterResource(id = HafthashtadIcons.Arrow),
                            contentDescription = null
                        )
                        Icon(
                            painter = painterResource(id = HafthashtadIcons.Arrow),
                            contentDescription = null
                        )
                        Icon(
                            painter = painterResource(id = HafthashtadIcons.Arrow),
                            contentDescription = null
                        )
                        Icon(
                            painter = painterResource(id = HafthashtadIcons.Arrow),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}


@Preview(
    name = "HafthashtadAlertDialog",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadAlertDialog",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadAlertDialogPreview() {
    HafthashtadTheme {
        Surface {
            HafthashtadAlertDialog(
                onDismiss = {},
                title = "title",
                body = "body",
                positiveTitle = "String",
                onPositiveClick = {}
            )
        }
    }
}

@Preview(
    name = "HafthashtadAlertDialogWithIcon",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "HafthashtadAlertDialogWithIcon",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HafthashtadAlertDialogWithIconPreview() {
    HafthashtadTheme {
        Surface {
            HafthashtadAlertDialogWithIcon(
                onDismiss = {},
                title = "title",
                body = "body",
                icon = null,
                positiveTitle = "String",
                negativeTitle = "cancel",
                onPositiveClick = {}
            )
        }
    }
}

@Composable
@Preview(
    name = "HafthashtadAlertDialogWithIcon",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
fun HafthashtadAlertErrorDialogPreview() {
    HafthashtadTheme {
        Surface {
            HafthashtadErrorDialog(
                onDismiss = {},
                title = "title",
                body = "body",
                positiveTitle = "String",
                onPositiveClick = {}
            )
        }
    }
}



