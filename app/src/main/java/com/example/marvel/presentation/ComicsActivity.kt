package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.presentation.epoxy.ComicsController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_comics.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsActivity : AppCompatActivity() {

    private val comicsViewModel: ComicsViewModel by viewModel()
    private val comicsController: ComicsController by lazy {
        ComicsController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        setupComicsRecycler()
        setupComicsViewModel()
    }

    private fun setupComicsRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        activityComicsList.apply {
            layoutManager = linearLayoutManager
            adapter = comicsController.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@ComicsActivity,
                    linearLayoutManager.orientation
                )
            )
        }
    }

    private fun setupComicsViewModel() {
        observeComics()
        comicsViewModel.getComicsList()
    }

    private fun observeComics() {
        comicsViewModel.comicsViewState.observe(this, Observer { comicsListViewState ->
            comicsController.comicsItems = comicsListViewState.comicsList
        })

        comicsViewModel.comicsAction.observe(this, Observer { action ->
            when(action){
                is ComicsAction.ShowSnackBar -> showSnackBar(action.message)
            }
        })
    }

    private fun showSnackBar(message: String){
        Snackbar.make(activityComicsList, message, Snackbar.LENGTH_SHORT).show()
    }


}
