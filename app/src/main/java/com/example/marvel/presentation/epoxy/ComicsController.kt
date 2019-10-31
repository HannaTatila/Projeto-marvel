package com.example.marvel.presentation.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.marvel.domain.Comics

class ComicsController: EpoxyController(){

    private var comicsItems : List<Comics> = listOf()

    fun setComicsData(newComicsItemst: List<Comics>) {
        comicsItems = newComicsItemst
        requestModelBuild()
    }

    override fun buildModels() {
        comicsItems.forEach {comic ->
            ComicsModel_()
                .id(comic.thumbnail)
                .thumbnail(comic.thumbnail)
                .title(comic.title)
                .addTo(this)
        }
    }
}
