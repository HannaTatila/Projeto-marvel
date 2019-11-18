package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
import java.time.Duration

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
        setupComicsViewModel()
    }

    private fun setupComicsRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        activityComicsList.apply {
            layoutManager = GridLayoutManager(this@ComicsActivity, COUNT_COLUMNS_GRID_LAYOUT)
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
            when (action) {
                is ComicsAction.ShowSnackBar -> showSnackBar(action.message)
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(activityComicsList, message, Snackbar.LENGTH_SHORT).show()
    }

}