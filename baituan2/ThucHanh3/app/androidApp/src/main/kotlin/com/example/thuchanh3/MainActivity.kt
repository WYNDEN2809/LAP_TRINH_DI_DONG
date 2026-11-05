package com.example.thuchanh3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button6)?.setOnClickListener {
            startActivity(Intent(this, TH3_bai1::class.java))
        }

        findViewById<Button>(R.id.button7)?.setOnClickListener {
            startActivity(Intent(this, TH3_bai2::class.java))
        }

        findViewById<Button>(R.id.button8)?.setOnClickListener {
            startActivity(Intent(this, TH3_bai3::class.java))
        }

        findViewById<Button>(R.id.button9)?.setOnClickListener {
            startActivity(Intent(this, TH3_bai4::class.java))
        }
    }
}




