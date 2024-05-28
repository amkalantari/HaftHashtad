package com.hafthashtad.android.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.data.model.previewProducts
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
internal fun ProductsItem(item: Products) {
    Column {
        Text(text = item.title)
    }
}

@Preview(
    name = "CountryListContent",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "CountryListContent",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewProductsItem() {
    HafthashtadTheme {
        HafthashtadBackground {
            ProductsItem(previewProducts)
        }
    }
}