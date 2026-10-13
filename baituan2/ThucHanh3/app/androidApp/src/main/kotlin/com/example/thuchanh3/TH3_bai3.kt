package com.example.thuchanh3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import androidx.collection.floatListOf
import androidx.core.graphics.toColorInt


class TH3_bai3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai3)

        val diemsv = listOf("8.5")
        val ktrthem = floatArrayOf(9f, 7f, 5.5f, 3f, 11f)
        var xeploai = ""


        val score = findViewById<TextView>(R.id.scoresv)
        score.text = diemsv[0]
        score.textSize = 24f
        score.gravity = Gravity.CENTER

        val loai = findViewById<TextView>(R.id.loai)
        val thexeploai = TextView(this)
        val diemso = diemsv[0].toFloat()
        xeploai = when {
            diemso >= 8.0 -> "Giỏi"
            diemso >= 6.5 -> "Khá"
            diemso >= 5.0 -> "Trung bình"
            else -> "Yếu"
        }
        loai.text = xeploai
        loai.textSize = 18f
        loai.gravity = Gravity.CENTER
        val ktrt = findViewById<LinearLayout>(R.id.ktt)
        for (so in ktrthem) {
            val hang = LinearLayout(this)
            hang.orientation = LinearLayout.HORIZONTAL
            hang.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val thektr = TextView(this)
            thektr.text = so.toString()
            thektr.textSize = 20f
            thektr.gravity = Gravity.LEFT

            val paramsDiem = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            thektr.layoutParams = paramsDiem
            xeploai = when {
                so > 10f -> "Không hợp lệ"
                so >= 8f -> "Giỏi"
                so >= 6.5f -> "Khá"
                so >= 5f -> "Trung bình"
                so >= 0f -> "Yếu"
                else -> "Không hợp lệ"
            }
            val thexl = TextView(this)
            thexl.text = xeploai
            thexl.textSize = 20f
            thexl.gravity = Gravity.RIGHT

            val paramsLoai = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            thexl.layoutParams = paramsLoai

            hang.addView(thektr)
            hang.addView(thexl)

            ktrt.addView(hang)


        }
        val btbackh3 = findViewById<Button>(R.id.btback3)
        btbackh3.setOnClickListener {
            finish()


        }
    }
}