package com.javimartd.theguardian.ui.news

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.extensions.*
import com.javimartd.theguardian.ui.news.model.NewsView
import kotlin.properties.Delegates

class NewsAdapter(private val readMoreClickListener: (NewsView) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var items: List<NewsView> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged() }

    /*var items = listOf<NewsView>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, readMoreClickListener)
    }

    /**
     * We're using encapsulation to achieve separation of concerns.
     * The constructor can only be called inside the class
     */
    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * ViewHolder is responsible to know what layout to inflate.
         */
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = parent.inflate(R.layout.item_recycler)
                return ViewHolder(view)
            }
        }

        private val textTitle: AppCompatTextView = itemView.findViewById(R.id.textTitle)
        private val textDate:  AppCompatTextView = itemView.findViewById(R.id.textDate)
        private val buttonReadMore: AppCompatButton = itemView.findViewById(R.id.buttonReadMore)
        private val chip: Chip = itemView.findViewById(R.id.some_chip)
        private val textDescription: AppCompatTextView = itemView.findViewById(R.id.textDescription)
        private val image: AppCompatImageView = itemView.findViewById(R.id.imageView)

        /**
         * Encapsulating the details of how to update the views into the ViewHolder which
         * has the views. That way if we add more ViewHolders we just need to call the
         * corresponding bind method.
         */
        fun bind(item: NewsView, readMoreClick: (NewsView) -> Unit) {
            Glide.with(image.ctx)
                    .load(item.thumbnail)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
            textTitle.text = item.title
            textDate.text = item.date.toLong(FORMAT_DATE_TIME_API).toDateString()
            chip.setChipBackgroundColorResource(item.section.getColor())
            chip.text = item.section.getTitle()
            textDescription.text = item.description
            buttonReadMore.setOnClickListener { readMoreClick(item) }
        }
    }
}