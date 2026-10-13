package com.example.thuchanh3

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge


class TH3_bai4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai4)

        fun xeploai(diem: Float?): String {
            if (diem == null){
                return "Chưa có điểm"
            }
            return when {
                diem > 10f -> "Không hợp lệ"
                diem >= 8f -> "Giỏi"
                diem >= 6.5f -> "Khá"
                diem >= 5f -> "Trung bình"
                diem >= 0f -> "Yếu"
                else -> "Không hợp lệ"
            }
        }

        class Student (
            val name: String,
            val Scoresv: Float?,
        )


        val dsSV = listOf(
            Student("Nguyễn Văn An", 9.0f),
            Student("Trần Thị Bình", 7.2f),
            Student("Lê Minh Chi", null),
            Student("Phạm Quốc Dũng", 4.5f),
            Student("Võ Thanh Em", 5.8f),
        )

        val dtb = findViewById<TextView>(R.id.txscore)
        var tongdiem = 0.0
        var sosvcodiem =0.0
        for (tbsv in dsSV){
            if(tbsv.Scoresv != null) {
                tongdiem += tbsv.Scoresv
                sosvcodiem++
            }
        }
        val scoretb  = if (sosvcodiem > 0){
            tongdiem/sosvcodiem
        }
        else 0.0
        dtb.text = String.format("%.2f",scoretb)

        val scoreG = findViewById<TextView>(R.id.ScoreG)
        val sosvNscore = findViewById<TextView>(R.id.SLNscore)
        val ScoreMax = findViewById<TextView>(R.id.SLMscore)
        var countsvG = 0
        var countsvNscore = 0
        var maxscore = 0f
        for (sv in dsSV ) {
            sv.Scoresv?.let {
                if (it >= maxscore){
                    maxscore = it
                }
            }
            val xeploai = xeploai(sv.Scoresv)
            if (xeploai == "Giỏi"){
                countsvG++
            }
            else if (xeploai == "Chưa có điểm"){
                countsvNscore++
            }
        }
        scoreG.text = countsvG.toString()
        sosvNscore.text = countsvNscore.toString()
        ScoreMax.text = maxscore.toString()
        val hiends = findViewById<LinearLayout>(R.id.hiendsSV)
        for (student in dsSV)   {
            val xeploai = xeploai(student.Scoresv)
            val thesv = TextView(this)
            thesv.text = """
                ${student.name}
                ${xeploai}
                ${student.Scoresv?: "_"}
            """.trimIndent()
            thesv.textSize =20f
            thesv.setPadding(24,24,24,24)
            thesv.setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 12, 0, 12)        // khoảng cách giữa các item
            }
            hiends.addView(thesv)

            val btbackh4=findViewById<Button>(R.id.btback4)
            btbackh4.setOnClickListener {
                finish()
            }

        }
    }
}