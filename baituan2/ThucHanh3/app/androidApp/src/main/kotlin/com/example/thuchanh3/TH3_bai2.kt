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
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt


class TH3_bai2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai2)
        class student(
            var name: String,
            var email: String?,
            var sdt: String?
        )

        val dsSV = listOf(
            student("Huy", null, "0922554531"),
            student("HuyThuHai", "huythuhai@.gmail.com", "0922554531"),
            student("hoàng","hh@gmail.com",null),
            student("khánh", "kh@gmail.com", "0895667768"),
            student("nhi",null, null )

        )
        val hiends = findViewById<LinearLayout>(R.id.containSV)
        for (student in dsSV)   {
            val thesv = TextView(this)
            thesv.text = """
                Tên: ${student.name}
                Email: ${student.email?: "Chưa cập nhật"}
                SĐT: ${student.sdt?: "Chưa cập nhật"}
            """.trimIndent()
            thesv.textSize =20f
            thesv.setPadding(24,24,24,24)

            hiends.addView(thesv)
        }
        val btbackh2=findViewById<Button>(R.id.btback2)
        btbackh2.setOnClickListener {
            finish()
        }
    }
}
