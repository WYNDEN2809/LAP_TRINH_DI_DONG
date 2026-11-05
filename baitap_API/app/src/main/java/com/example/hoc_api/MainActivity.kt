package com.example.hoc_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hoc_api.ui.ProductDetailScreen
import com.example.hoc_api.ui.theme.Hoc_apiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hoc_apiTheme {
                ProductDetailScreen(
                    onBackClick = { finish() }
                )
            }
        }
    }
}
