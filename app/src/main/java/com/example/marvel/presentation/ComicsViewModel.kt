package com.example.marvel.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel.domain.Comics

class ComicsViewModel : ViewModel() {
    val comicsListLiveData: MutableLiveData<List<Comics>> = MutableLiveData()

    fun getComicsList() {
        //só para teste
        comicsListLiveData.value = makeComicsListFake()
    }

    private fun makeComicsListFake(): MutableList<Comics>{
        val comicsListFake: MutableList<Comics> = mutableListOf()
        for (id in 0..5) {
            val comic = Comics(
                "Título $id",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/80/4bc5fe7a308d7/portrait_medium.jpg",
                "price $id",
                "creator $id",
                "characters $id"
            )
            comicsListFake.add(comic)
        }
        return comicsListFake
    }
}