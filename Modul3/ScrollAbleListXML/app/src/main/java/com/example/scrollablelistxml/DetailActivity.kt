package com.example.scrollablelistxml

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("id", -1)
        val chocolate = DataProvider.chocolateList.find { it.id == id }

        chocolate?.let {
            findViewById<ImageView>(R.id.imageChocolateDetail).setImageResource(it.chocolateImageId)
            findViewById<TextView>(R.id.textTitleDetail).text = it.name
            findViewById<TextView>(R.id.textDescriptionDetail).text = it.description
        }
    }
}
