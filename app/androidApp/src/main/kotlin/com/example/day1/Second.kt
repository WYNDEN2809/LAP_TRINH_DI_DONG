package com.example.day1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.EditText

class Second : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        val name2 = findViewById<EditText>(R.id.editTextText1)
        val namehientai= intent.getStringExtra("name")
        name2.setText(namehientai)

        val button = findViewById<Button>(R.id.buttonsubmit)
        button.setOnClickListener {
            val txtNameNew = findViewById<EditText>(R.id.editTextText1)
            val newName = txtNameNew.text.toString()
            val name1 = txtNameNew.text.toString()
            val trove = Intent(this, MainActivity::class.java)
                intent.putExtra("namemoi", newName)
                startActivity(trove)


        }
    }
}
