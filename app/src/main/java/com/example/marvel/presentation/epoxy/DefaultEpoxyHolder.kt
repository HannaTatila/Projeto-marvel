package com.example.marvel.presentation.epoxy

import android.view.View
import androidx.annotation.CallSuper
import com.airbnb.epoxy.EpoxyHolder

open class DefaultEpoxyHolder : EpoxyHolder() {

    lateinit var itemView: View

    @CallSuper
    override fun bindView(itemView: View) {
        this.itemView = itemView
    }

}