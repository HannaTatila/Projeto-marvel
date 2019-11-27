package com.example.marvel.presentation.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.marvel.R
import kotlinx.android.synthetic.main.item_list_comics.view.*

@EpoxyModelClass(layout = R.layout.item_list_comics)
abstract class ComicsModel : EpoxyModelWithHolder<DefaultEpoxyHolder>() {

    @EpoxyAttribute(hash = true)
    var thumbnail: String = ""

    @EpoxyAttribute(hash = true)
    var title: String = ""

    @EpoxyAttribute(hash = false)
    var callbackComic: (() -> Unit)? = null

    override fun bind(holder: DefaultEpoxyHolder) {
        Glide.with(holder.itemView.context)
            .load(thumbnail)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(VALUE_ROUNDER_CORNERS)))
            .into(holder.itemView.iv_thumbnail)
        holder.itemView.tv_title.text = title
        holder.itemView.setOnClickListener { callbackComic?.invoke() }
    }

    companion object {
        private const val VALUE_ROUNDER_CORNERS = 35
    }

}