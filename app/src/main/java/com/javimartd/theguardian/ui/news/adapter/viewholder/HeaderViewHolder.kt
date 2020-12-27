package com.javimartd.theguardian.ui.news.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import com.javimartd.theguardian.ui.news.model.HeaderView
import kotlinx.android.synthetic.main.item_header.view.*

class HeaderViewHolder(val view: View): BaseViewHolder<Visitable>(view) {

    companion object {
        const val LAYOUT = R.layout.item_header
    }

    private val textHeader: TextView = view.textHeader

    override fun bind(model: Visitable) {
        model as HeaderView
        textHeader.text = textHeader.context.getString(model.getTitle())
    }
}