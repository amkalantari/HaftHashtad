package com.hafthashtad.android.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Language
import androidx.compose.ui.graphics.vector.ImageVector
import com.hafthashtad.android.core.designsystem.R

/**
 * Hafthashtad icons. Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object HafthashtadIcons {
    val Circle = Icons.Rounded.Circle

    val Browser = Icons.Rounded.Language

    val Back = R.drawable.arrow_back_24px

    val Arrow = R.drawable.arrow

    val ArrowDropDown = Icons.Rounded.ArrowDropDown
    val ArrowDropUp = Icons.Rounded.ArrowDropUp

    val Add = R.drawable.add_24px
    val Minus = R.drawable.baseline_minimize_24
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class HafthashtadIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : HafthashtadIcon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : HafthashtadIcon()
}
