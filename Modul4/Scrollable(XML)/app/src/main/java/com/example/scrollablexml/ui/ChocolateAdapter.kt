package com.example.scrollablexml.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablexml.R
import com.example.scrollablexml.data.Chocolate

class ChocolateAdapter(
    private val context: Context,
    private val chocolateList: List<Chocolate>,
    private val onDetailClick: (Chocolate) -> Unit,
    private val onLinkClick: (Chocolate) -> Unit
) : RecyclerView.Adapter<ChocolateAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageChocolate)
        val name: TextView = view.findViewById(R.id.textName)
        val description: TextView = view.findViewById(R.id.textDescription)
        val btnImdb: Button = view.findViewById(R.id.buttonImdb)
        val btnDetail: Button = view.findViewById(R.id.buttonDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_chocolate, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = chocolateList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chocolate = chocolateList[position]
        holder.image.setImageResource(chocolate.chocolateImageId)
        holder.name.text = chocolate.name
        holder.description.text = chocolate.description

        holder.btnImdb.setOnClickListener {
            onLinkClick(chocolate)
        }

        holder.btnDetail.setOnClickListener {
            onDetailClick(chocolate)
        }
    }
}