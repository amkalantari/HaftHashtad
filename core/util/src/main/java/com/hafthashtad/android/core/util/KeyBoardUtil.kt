package com.hafthashtad.android.core.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

object KeyBoardUtil {
    fun copyText(context: Context, label: String, textToCopy: String) {

        val clipboardManager: ClipboardManager? =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        if (clipboardManager != null && getClipboardText(context) != textToCopy) {
            val clipData = ClipData.newPlainText(label, textToCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getClipboardText(context: Context): String {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
            val clipData = clipboardManager.primaryClip
            val item = clipData!!.getItemAt(0)
            val clipboardText = item.text
            if (clipboardText != null) {
                return clipboardText.toString()
            }
        }
        return ""
    }
}