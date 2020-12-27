package com.javimartd.theguardian.ui.news.adapter.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.extensions.FORMAT_DATE_TIME_API
import com.javimartd.theguardian.ui.extensions.ctx
import com.javimartd.theguardian.ui.extensions.toDateString
import com.javimartd.theguardian.ui.extensions.toLong
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import com.javimartd.theguardian.ui.news.model.NewsView
import com.javimartd.theguardian.ui.webView.WebViewActivity
import org.jetbrains.anko.startActivity

class NewsViewHolder(view: View): BaseViewHolder<Visitable>(view) {

    companion object {
        const val LAYOUT = R.layout.item_recycler
    }

    private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
    private val textDate: TextView = itemView.findViewById(R.id.textDate)
    private val buttonReadMore: Button = itemView.findViewById(R.id.buttonReadMore)
    private val chip: Chip = itemView.findViewById(R.id.some_chip)
    private val textDescription: TextView = itemView.findViewById(R.id.textDescription)
    private val image: ImageView = itemView.findViewById(R.id.imageView)

    override fun bind(model: Visitable) {
        model as NewsView
        Glide.with(image.ctx)
                .load(model.thumbnail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        textTitle.text = model.title
        textDate.text = model.date.toLong(FORMAT_DATE_TIME_API).toDateString()
        chip.setChipBackgroundColorResource(model.section.getColor())
        chip.text = model.section.getTitle()
        textDescription.text = model.description
        buttonReadMore.setOnClickListener {
            buttonReadMore.context.startActivity<WebViewActivity>(WebViewActivity.URL to model.webUrl)
        }
    }
}