package com.example.marvel.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.presentation.epoxy.ComicsController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val comicsViewModel: ComicsViewModel by viewModel()
    private val comicsController: ComicsController by lazy { ComicsController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupComicsRecycler()
    }

    private fun setupComicsRecycler() {
        val linearLayoutManager = LinearLayoutManager(context)
        erv_comicsList.apply {
            layoutManager = GridLayoutManager(context, COUNT_COLUMNS_GRID_LAYOUT)
            setControllerAndBuildModels(comicsController)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
        }

        comicsController.setOnClickListenerComic { idComic ->
            comicsViewModel.navigateToDetailsComicActivity(idComic)
        }
    }

    private fun setupComicsViewModel() {
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
        val intent = DetailsComicActivity.newInstance(context!!, idComic)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        setupComicsViewModel()
    }

    companion object {
        private const val COUNT_COLUMNS_GRID_LAYOUT = 2
    }

}