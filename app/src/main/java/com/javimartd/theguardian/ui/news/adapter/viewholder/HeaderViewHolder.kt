package com.javimartd.theguardian.ui.news.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import com.javimartd.theguardian.ui.news.model.HeaderView

class HeaderViewHolder(val view: View): BaseViewHolder<Visitable>(view) {

    companion object {
        const val LAYOUT = R.layout.item_header
    }

    private val textHeader: TextView = itemView.findViewById(R.id.textHeader)

    override fun bind(model: Visitable) {
        model as HeaderView
        textHeader.text = textHeader.context.getString(model.getTitle())
    }
}