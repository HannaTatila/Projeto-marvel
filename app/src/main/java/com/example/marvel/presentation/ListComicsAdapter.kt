package com.example.marvel.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.domain.Comics
import kotlinx.android.synthetic.main.item_list_comics.view.*

class ListComicsAdapter(private val listComics: List<Comics>) : RecyclerView.Adapter<ListComicsAdapter.ListComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListComicsViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_comics, parent, false)

        return ListComicsViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return listComics.count()
    }

    override fun onBindViewHolder(holder: ListComicsViewHolder, position: Int) {
        holder.dataBind(listComics[position])
    }

    class ListComicsViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        private val thumbnailComic = itemView.activityComicsThumbnail
        private val titleComic = itemView.activityComicsTitleComic

        fun dataBind(comic: Comics) {
            Glide.with(context).load(comic.thumbnail).into(thumbnailComic)
            titleComic.text = comic.title
        }
    }

}