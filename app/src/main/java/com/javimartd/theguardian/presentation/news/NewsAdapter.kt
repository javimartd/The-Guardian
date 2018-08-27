package com.javimartd.theguardian.presentation.news

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.javimartd.theguardian.R
import com.javimartd.theguardian.domain.model.DailyNews
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.presentation.extensions.inflate
import com.javimartd.theguardian.presentation.extensions.loadUrl

class NewsAdapter(private val items: DailyNews, private val itemClick: (News) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_recycler)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = items.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.image.loadUrl(thumbnail)
            //holder.textDate.text = date
            holder.textTitle.text = title
            holder.textTitle.setOnClickListener { itemClick(this) }
        }
    }

    class ViewHolder(itemView: View?, val itemClick: (News) -> Unit) : RecyclerView.ViewHolder(itemView) {
        //val textDate = itemView!!.findViewById<TextView>(R.id.textDate)!!
        val textTitle = itemView!!.findViewById<TextView>(R.id.textTitle)!!
        val image = itemView!!.findViewById<AppCompatImageView>(R.id.imageView)!!
    }
}