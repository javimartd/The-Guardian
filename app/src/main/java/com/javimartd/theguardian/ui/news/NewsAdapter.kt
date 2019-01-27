package com.javimartd.theguardian.ui.news

import android.support.design.chip.Chip
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.extensions.inflate
import com.javimartd.theguardian.ui.extensions.loadUrl
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
            holder.chip.setChipBackgroundColorResource(section.getColor())
            holder.chip.text = section.getTitle()
            holder.buttonReadMore.setOnClickListener { readMoreClick(this) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: AppCompatTextView = itemView.findViewById(R.id.textTitle)
        val buttonReadMore: AppCompatButton = itemView.findViewById(R.id.buttonReadMore)
        val chip: Chip = itemView.findViewById(R.id.some_chip)
        val image: AppCompatImageView = itemView.findViewById(R.id.imageView)
    }
}