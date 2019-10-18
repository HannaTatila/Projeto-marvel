package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.marvel.R
import kotlinx.android.synthetic.main.activity_comics.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsActivity : AppCompatActivity() {

    private val comicsViewModel: ComicsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        setupComicsViewModel()
    }

    private fun setupComicsViewModel() {
        comicsObserver(comicsViewModel)
        comicsViewModel.getComicsList()
    }

    private fun comicsObserver(comicsViewModel: ComicsViewModel) {
        comicsViewModel.comicsListLiveData.observe(this, Observer {
            it?.let{comicsList ->
                with(activityComicsList) {
                    val comicsAdapter = ListComicsAdapter(comicsList)
                    setHasFixedSize(true)
                    adapter = comicsAdapter
                }
            }
        })
    }
}
