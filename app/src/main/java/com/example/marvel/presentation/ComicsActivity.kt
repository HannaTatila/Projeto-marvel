package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.presentation.epoxy.ComicsController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_home.*

class ComicsActivity : AppCompatActivity() {

    private val comicsViewModel: ComicsViewModel by viewModel()
    private val comicsController: ComicsController by lazy { ComicsController() }

    companion object {
        private const val COUNT_COLUMNS_GRID_LAYOUT = 2
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bnv_navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setupComicsRecycler()
    }

    private fun setupComicsRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        erv_comicsList.apply {
            layoutManager = GridLayoutManager(this@ComicsActivity, COUNT_COLUMNS_GRID_LAYOUT)
            setControllerAndBuildModels(comicsController)
            addItemDecoration(
                DividerItemDecoration(
                    this@ComicsActivity,
                    linearLayoutManager.orientation
                )
            )
        }

        comicsController.setOnClickComic { idComic ->
            comicsViewModel.navigateToDetailsComicActivity(idComic)
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
            when (action) {
                is ComicsAction.ShowSnackBar -> showSnackBar(action.message)
                is ComicsAction.NavigateToDetailsComicActivity -> startDetailsComic(action.idComic)
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(erv_comicsList, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun startDetailsComic(idComic: Int) {
        val intent = DetailsComicActivity.newInstance(this, idComic)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        setupComicsViewModel()
    }

}