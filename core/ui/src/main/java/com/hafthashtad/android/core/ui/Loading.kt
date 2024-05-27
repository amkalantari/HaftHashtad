package com.hafthashtad.android.core.ui

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hafthashtad.android.core.designsystem.R
import com.hafthashtad.android.core.designsystem.component.HafthashtadAnimation

@Composable
fun HafthashtadLoading(
    modifier: Modifier,
    @RawRes jsonRes: Int = R.raw.loading,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HafthashtadAnimation(jsonRes = jsonRes, modifier = Modifier.size(240.dp))
    }
}