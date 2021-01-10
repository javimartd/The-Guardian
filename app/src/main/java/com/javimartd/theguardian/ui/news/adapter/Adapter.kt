package com.javimartd.theguardian.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javimartd.theguardian.ui.news.adapter.viewholder.BaseViewHolder
import com.javimartd.theguardian.ui.news.adapter.visitor.TypeFactory
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import kotlin.properties.Delegates

class Adapter(private val typeFactory: TypeFactory): RecyclerView.Adapter<BaseViewHolder<Visitable>>() {

    var items: List<Visitable> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Visitable> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.holder(viewType, view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<Visitable>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].type(typeFactory)
}