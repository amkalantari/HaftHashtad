package com.hafthashtad.android.core.designsystem.component

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(modifier: Modifier, url: String) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier,
        factory = {
            WebView(context).apply {

                webViewClient = object : WebViewClient() {


                    override fun shouldInterceptRequest(
                        view: WebView,
                        request: WebResourceRequest
                    ): WebResourceResponse? {
                        val _url = request.url.toString()

                        if (_url.startsWith("http://api.qrserver.com/v1/create-qr-code")) {
                            val httpsUrl = _url.replace("http://", "https://")
                            try {
                                val connection = URL(httpsUrl).openConnection() as HttpURLConnection
                                connection.requestMethod = "GET"
                                connection.connect()

                                val mimeType = connection.contentType
                                val charset = connection.contentEncoding ?: "UTF-8"

                                return WebResourceResponse(
                                    mimeType,
                                    charset,
                                    connection.inputStream
                                )

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }

                        return super.shouldInterceptRequest(view, request)
                    }
                }

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }

                loadUrl(url)

            }
        },
        update = {
            it.loadUrl(url)
        }
    )
}