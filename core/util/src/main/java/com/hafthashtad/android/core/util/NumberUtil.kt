package com.hafthashtad.android.core.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

object NumberUtil {

    fun getPersianNumberFormat(number: Long): String {
        val numberFormat = NumberFormat.getInstance(Locale("fa", "IR"));
        return numberFormat.format(number)
    }

    fun getEnglishNumberFormat(number: Double): String {
        val numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        return numberFormat.format(number.toLong())
    }

    fun percentage(value: Double, target: Double?) = when {
        target == null && value == 0.0 -> 0.0
        target == null && value > 0.0 -> Double.POSITIVE_INFINITY
        target == null && value < 0.0 -> Double.NEGATIVE_INFINITY
        target == 0.0 -> Double.POSITIVE_INFINITY
        else -> {
            requireNotNull(target)
            ((value - target) / target) * 100
        }
    }

    fun percentageFormat(value: Double) = when (value) {
        0.0 -> "0%"
        Double.POSITIVE_INFINITY -> "∞%"
        Double.NEGATIVE_INFINITY -> "-∞%"
        else -> "%.1f%%".format(value)
    }

    fun getValueFormatted(value: Double): String {
        val df = DecimalFormat("0.0").apply {
            roundingMode = RoundingMode.FLOOR
        }
        val absValue = abs(value)
        return if (absValue < 1000) {
            df.format(value)
        } else {
            val exp = (ln(absValue) / ln(1000.0)).toInt()
            String.format(
                "%s%c",
                df.format((value / 1000.0.pow(exp.toDouble()))),
                "KMBTPE"[exp - 1]
            )
        }
    }

    fun getDifferenceValueLabel(value: Double, target: Double) = if (value >= target) {
        "Over Target"
    } else {
        "Left To Target"
    }
}