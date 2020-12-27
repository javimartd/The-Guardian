package com.javimartd.theguardian.ui.news.adapter.viewholder

import android.content.Intent
import android.net.Uri
import android.view.View
import com.google.android.material.button.MaterialButton
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable

class AdViewHolder(view: View): BaseViewHolder<Visitable>(view) {

    private val materialButtonSubscribe: MaterialButton = itemView.findViewById(R.id.materialButtonSubscribe)
    private val materialButtonContribute: MaterialButton = itemView.findViewById(R.id.materialButtonContribute)

    companion object {
        const val LAYOUT = R.layout.item_ad
        private const val URL_SUBSCRIBE = "https://support.theguardian.com/uk/subscribe"
        private const val URL_CONTRIBUTE = "https://support.theguardian.com/eu/contribute?CMP=ppc_mem_&gclid=Cj0KCQiA_qD_BRDiARIsANjZ2LCELcTFaUYCvE50a0gBQt10Dl0fQ5-0N2aMVkn4b2HABhXrpKmAPCQaAtKGEALw_wcB"
    }

    override fun bind(model: Visitable) {
        materialButtonSubscribe.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(URL_SUBSCRIBE))
            materialButtonSubscribe.context.startActivity(intent)
        }
        materialButtonContribute.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(URL_CONTRIBUTE))
            materialButtonSubscribe.context.startActivity(intent)
        }
    }

}