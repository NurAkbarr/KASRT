package com.example.kasrt

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class CCTVActivity : AppCompatActivity() {

    private lateinit var webViewCCTV: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cctv)

        // Inisialisasi WebView
        webViewCCTV = findViewById(R.id.webViewCCTV)

        // Aktifkan JavaScript
        val webSettings: WebSettings = webViewCCTV.settings
        webSettings.javaScriptEnabled = true

        // Muat URL CCTV dari web
        val cctvUrl = "https://jid.jasamarga.com/cctv2/0f6bd5f?tx=1646733773121&t=20220915111114502"
        webViewCCTV.loadUrl(cctvUrl)
    }
}
