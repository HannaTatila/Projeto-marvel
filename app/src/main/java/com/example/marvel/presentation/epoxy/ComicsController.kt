package com.example.marvel.presentation.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.marvel.domain.Comics

class ComicsController : EpoxyController() {

    var onClickListener: ((Int) -> Unit)? = null

    var comicsItems: List<Comics> = listOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        comicsItems.forEach { comic ->
            ComicsModel_()
                .id(comic.id)
                .thumbnail(comic.thumbnail)
                .title(comic.title)
                .onClickListener { onClickListener?.invoke(comic.id) }
                .addTo(this)
        }
    }

    fun setOnClickListenerComic(click: (idComic: Int) -> Unit) {
        this.onClickListener = click
    }

}