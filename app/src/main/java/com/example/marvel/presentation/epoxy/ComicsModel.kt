package com.example.marvel.presentation.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.marvel.R
import kotlinx.android.synthetic.main.item_list_comics.view.*

@EpoxyModelClass(layout = R.layout.item_list_comics)
abstract class ComicsModel : EpoxyModelWithHolder<DefaultEpoxyHolder>() {

    @EpoxyAttribute(hash = true)
    var thumbnail: String = ""

    @EpoxyAttribute(hash = true)
    var title: String = ""

    override fun bind(holder: DefaultEpoxyHolder) {
        Glide.with(holder.itemView.context).load(thumbnail).into(holder.itemView.activityComicsThumbnail)
        holder.itemView.activityComicsTitleComic.text = title
    }

}