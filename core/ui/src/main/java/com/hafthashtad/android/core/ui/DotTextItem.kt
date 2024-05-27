package com.hafthashtad.android.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcon
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
fun DotTexts(
    modifier: Modifier = Modifier,
    textColor: Color,
    texts: List<String>
) {

    Column(modifier = modifier.fillMaxWidth()) {
        texts.forEachIndexed { index, text ->
            if (index != 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
            ) {
                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline),
                    imageVector = HafthashtadIcon.ImageVectorIcon(HafthashtadIcons.Circle).imageVector,
                    contentDescription = "Dot",
                    modifier = Modifier.padding(top=4.dp).size(8.dp)
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun PreviewDotTextItem() {
    HafthashtadTheme {
        HafthashtadBackground {
            DotTexts(
                modifier = Modifier.fillMaxWidth(),
                textColor = MaterialTheme.colorScheme.onSurface,
                texts = listOf("HomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValueHomeContract.initValue"),
            )
        }
    }
}
