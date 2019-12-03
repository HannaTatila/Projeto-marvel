package com.example.marvel.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R
import kotlinx.android.synthetic.main.activity_details_comic.fab

class DetailsComicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_comic)

        setupFabFavorite()
    }

    private fun setupFabFavorite() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Favorite comic!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            // TODO: salvar comic no banco
        }
    }

    companion object {
        private const val ARG_ID_COMIC = "arg_id_comic"

        fun newInstance(context: Context, idComic: Int): Intent {
            val bundle = Bundle()
            bundle.putInt(ARG_ID_COMIC, idComic)
            return Intent(context, DetailsComicActivity::class.java)
        }
    }

}