package com.eunidev.exampleidnnewsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunidev.exampleidnnewsapp.R
import com.eunidev.exampleidnnewsapp.data.NewsResponse
import com.eunidev.exampleidnnewsapp.database.NewsArticles

class RVMainAdapter(private val context: Context, private var list: List<NewsArticles>) : RecyclerView.Adapter<RVMainAdapter.ViewHolder>() {

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val image = v.findViewById<ImageView>(R.id.imageViewNewsImage_MainFragment)
        val tvTitle = v.findViewById<TextView>(R.id.textViewTitle_MainFragment)
        val tvDescription = v.findViewById<TextView>(R.id.textViewDescription_MainFragment)
        val tvPublish = v.findViewById<TextView>(R.id.textViewPublishedAt_MainFragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = list[position]

        Glide.with(context)
            .load(currentData.urlToImage)
            .into(holder.image)

        holder.tvTitle.text = currentData.title
        holder.tvDescription.text = currentData.description
        holder.tvPublish.text = currentData.publishedAt
    }

    override fun getItemCount(): Int = list.size

    fun update(data: List<NewsArticles>) {
        list = data
        notifyDataSetChanged()
    }
}