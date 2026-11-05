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
import androidx.core.graphics.toColorInt

class TH3_bai1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai1)


        val txtfirts = findViewById<EditText>(R.id.editTextText)
        val txtsecond = findViewById<EditText>(R.id.editTextText2)
        val txtdapan = findViewById<TextView>(R.id.textView2)
        var pheptinh: String? = ""
        val btplus = findViewById<Button>(R.id.button)
        val btsubtrac = findViewById<Button>(R.id.button2)
        val btmulti = findViewById<Button>(R.id.button3)
        val btdivision = findViewById<Button>(R.id.button4)

        fun setBorder(
            button: Button,
            strokeWidth: Int,
            strokeColor: Int,
            bgColor: Int = Color.LTGRAY
        ) {
            val drawable = GradientDrawable()
            drawable.setColor(bgColor)
            drawable.setStroke(strokeWidth, strokeColor)
            drawable.cornerRadius = 12f
            button.background = drawable
        }

        fun highlight(selected: Button) {
            setBorder(btplus, 2, Color.GRAY)
            setBorder(btsubtrac, 2, Color.GRAY)
            setBorder(btmulti, 2, Color.GRAY)
            setBorder(btdivision, 2, Color.GRAY)
            setBorder(selected, 12, "#4CAF50".toColorInt())
        }

        btplus.setOnClickListener { pheptinh = "+"; highlight(btplus) }
        btsubtrac.setOnClickListener { pheptinh = "-"; highlight(btsubtrac) }
        btmulti.setOnClickListener { pheptinh = "*"; highlight(btmulti) }
        btdivision.setOnClickListener { pheptinh = "/"; highlight(btdivision) }

        val btsubmit = findViewById<Button>(R.id.button5)
        btsubmit.setOnClickListener {
            val numberfirts: Double? = txtfirts.text.toString().toDoubleOrNull()
            val numbersecond: Double? = txtsecond.text.toString().toDoubleOrNull()
            if (numberfirts == null || numbersecond == null) {
                txtdapan.text = "Vui lòng nhập đủ 2 số"
                return@setOnClickListener
            }
            val dapan = when (pheptinh) {
                "+" -> numberfirts?.plus(numbersecond)
                "-" -> numberfirts?.minus(numbersecond)
                "*" -> numberfirts?.times(numbersecond)
                "/" -> numberfirts?.div(numbersecond)
                else -> {
                    txtdapan.text = "Vui lòng nhập phép tính vào"
                    return@setOnClickListener
                }
            }
            txtdapan.text = dapan.toString()

        }

        val btbackh1=findViewById<Button>(R.id.btback1)
        btbackh1.setOnClickListener {
            finish()
        }

        setBorder(btplus, 2, Color.GRAY)
        setBorder(btsubtrac, 2, Color.GRAY)
        setBorder(btmulti, 2, Color.GRAY)
        setBorder(btdivision, 2, Color.GRAY)
    }
}