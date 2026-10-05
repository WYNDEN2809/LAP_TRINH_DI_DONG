package com.example.day1

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.content.Intent
import android.widget.TextView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtName = findViewById<TextView>(R.id.textView1)
        val name1 = txtName.text.toString()

        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            finish()
        }
        val buttonedit = findViewById<Button>(R.id.button2)
        buttonedit.setOnClickListener {
            val intent = Intent(this, Second::class.java)
            intent.putExtra("name", name1)
            startActivity(intent)
        }
        val tenmoi = intent.getStringExtra("namemoi")
        txtName.text = tenmoi
    }
}





