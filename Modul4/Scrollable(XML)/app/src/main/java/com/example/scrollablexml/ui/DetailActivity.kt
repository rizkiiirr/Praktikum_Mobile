package com.example.scrollablexml.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollablexml.R
import com.example.scrollablexml.data.ChocolateProvider
import timber.log.Timber

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("DetailActivity opened")

        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra("id", -1)
        val chocolate = ChocolateProvider.chocolateList.find { it.id == id }

        chocolate?.let {
            Timber.d("Displaying chocolate detail: $it")
            findViewById<ImageView>(R.id.imageChocolateDetail).setImageResource(it.chocolateImageId)
            findViewById<TextView>(R.id.textTitleDetail).text = it.name
            findViewById<TextView>(R.id.textDescriptionDetail).text = it.description
        }
    }
}