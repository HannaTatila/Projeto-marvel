package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.di.comicsModule
import kotlinx.android.synthetic.main.activity_comics.*
import org.koin.core.context.startKoin

class ComicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        //startKoin(this, listOf(comicsModule))

        setupComicsViewModel()
    }

    private fun setupComicsViewModel() {
        val comicsViewModel = ViewModelProviders.of(this).get(ComicsViewModel::class.java)
        comicsObserver(comicsViewModel)
        comicsViewModel.getComicsList()
    }

    private fun comicsObserver(comicsViewModel: ComicsViewModel) {
        comicsViewModel.comicsListLiveData.observe(this, Observer {
            it?.let{comicsList ->
                with(activityComicsList) {
                    val comicsAdapter = ListComicsAdapter(comicsList)
                    layoutManager = LinearLayoutManager(this@ComicsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = comicsAdapter
                }
            }
        })
    }
}
