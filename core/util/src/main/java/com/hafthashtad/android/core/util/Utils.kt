package com.hafthashtad.android.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object Utils {

    fun openBrowser(link: String, context: Context) {
        var url = link
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://$url"
        }
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun shareDialog(text :String,subject:String,context: Context){
        val shareData = Intent(Intent.ACTION_SEND)
        shareData.type = "text/plain"
        shareData.putExtra(Intent.EXTRA_SUBJECT, subject)
        shareData.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(shareData, "Share In"))
    }
}