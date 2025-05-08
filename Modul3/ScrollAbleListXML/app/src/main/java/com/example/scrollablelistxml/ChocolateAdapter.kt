package com.example.scrollablelistxml

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ChocolateAdapter(private val context: Context, private val chocolateList: List<Chocolate>) :
    RecyclerView.Adapter<ChocolateAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageChocolate)
        val name: TextView = view.findViewById(R.id.textName)
        val description: TextView = view.findViewById(R.id.textDescription)
        val btnImdb: Button = view.findViewById(R.id.buttonImdb)
        val btnDetail: Button = view.findViewById(R.id.buttonDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_coklat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = chocolateList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chocolate = chocolateList[position]
        holder.image.setImageResource(chocolate.chocolateImageId)
        holder.name.text = chocolate.name
        holder.description.text = chocolate.description

        holder.btnImdb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(chocolate.btnLink))
            context.startActivity(intent)
        }

        holder.btnDetail.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("id", chocolate.id)
            }
            context.startActivity(intent)
        }
    }
}
