package com.javimartd.theguardian.ui.news

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.extensions.*
import com.javimartd.theguardian.ui.news.model.NewsViewModel
import kotlin.properties.Delegates

class NewsAdapter(private val readMoreClick: (NewsViewModel) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var items: List<NewsViewModel> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_recycler)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.image.loadUrl(thumbnail)
            holder.textTitle.text = title
            holder.textDate.text = date.toLong(FORMAT_DATE_TIME_API).toDateString()
            holder.chip.setChipBackgroundColorResource(section.getColor())
            holder.chip.text = section.getTitle()
            holder.textDescription.text = description
            holder.buttonReadMore.setOnClickListener { readMoreClick(this) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: AppCompatTextView = itemView.findViewById(R.id.textTitle)
        val textDate:  AppCompatTextView = itemView.findViewById(R.id.textDate)
        val buttonReadMore: AppCompatButton = itemView.findViewById(R.id.buttonReadMore)
        val chip: Chip = itemView.findViewById(R.id.some_chip)
        val textDescription: AppCompatTextView = itemView.findViewById(R.id.textDescription)
        val image: AppCompatImageView = itemView.findViewById(R.id.imageView)
    }
}