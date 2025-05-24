package com.example.scrollablexml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablexml.ui.ChocolateAdapter
import com.example.scrollablexml.ui.DetailActivity
import com.example.scrollablexml.viewmodel.ChocolateViewModel
import com.example.scrollablexml.viewmodel.ChocolateViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val viewModel: ChocolateViewModel by viewModels {
        ChocolateViewModelFactory("MainActivity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.chocolateList.collect { chocolate ->
                recyclerView.adapter = ChocolateAdapter(this@MainActivity, chocolate,
                    onDetailClick = {
                        Timber.d("Detail clicked: $it")
                        viewModel.onChocolateSelected(it)
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("id", it.id)
                        startActivity(intent)
                    },
                    onLinkClick = {
                        Timber.d("IMDB clicked: ${it.btnLink}")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.btnLink))
                        startActivity(intent)
                    }
                )
            }
        }
    }
}