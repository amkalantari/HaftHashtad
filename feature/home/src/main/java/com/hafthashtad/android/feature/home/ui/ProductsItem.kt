package com.hafthashtad.android.feature.home.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.data.model.previewProducts
import com.hafthashtad.android.core.designsystem.component.HafthashtadDivider
import com.hafthashtad.android.core.designsystem.icon.HafthashtadIcons
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme

@Composable
internal fun ProductsItem(product: Products) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(product.image).build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(86.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(62.dp))
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = product.title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${product.price} $",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {

                        Text(
                            text = "${product.rating.rate}/5",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "Star"
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            HafthashtadDivider()

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedIconButton(
                modifier = Modifier.size(32.dp),
                onClick = {
                    expanded.value = !expanded.value
                }
            ) {
                Image(
                    imageVector = if (expanded.value) {
                        HafthashtadIcons.KeyboardArrowUp
                    } else {
                        HafthashtadIcons.KeyboardArrowDown
                    },
                    contentDescription = "Arrow",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }

            if (expanded.value) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            AnimatedVisibility(visible = expanded.value) {
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
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
            LazyColumn {
                item {
                    ProductsItem(previewProducts)
                }
            }
        }
    }
}