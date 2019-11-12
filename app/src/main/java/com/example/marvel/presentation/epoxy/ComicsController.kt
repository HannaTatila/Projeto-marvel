package com.example.marvel.presentation.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.marvel.domain.Comics

class ComicsController : EpoxyController() {

    var comicsItems: List<Comics> = listOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        comicsItems.forEach { comic ->
            ComicsModel_()
                .id(comic.title)
                .thumbnail(comic.thumbnail)
                .title(comic.title)
                .addTo(this)
        }
    }
}
