package com.hafthashtad.android.core.network.retrofit.model.hafthashtad

import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.data.model.Rating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [Products]
 */

@Serializable
data class NetworkProducts(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Double,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val image: String,
    @SerialName("rating")
    val rating: NetworkRating,
)

@Serializable
data class NetworkRating(
    @SerialName("rate")
    val rate: Double,
    @SerialName("count")
    val count: Int
)

fun NetworkProducts.asExternalModel() = Products(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.asExternalModel()
)


fun NetworkRating.asExternalModel() = Rating(
    rate, count
)