package com.example.marvel.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvel.core.SingleLiveEvent
import com.example.marvel.domain.Comics
import com.example.marvel.domain.usecase.ComicsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComicsViewModel(application: Application,
                      private val comicsUseCase: ComicsUseCase
) : AndroidViewModel(application) {

    private val comicsListMutableLiveData: MutableLiveData<ComicsViewState> = MutableLiveData()
    val comicsViewState: LiveData<ComicsViewState> = comicsListMutableLiveData

    private val comicsActionSingleLive = SingleLiveEvent<ComicsAction>()
    val comicsAction: LiveData<ComicsAction> = comicsActionSingleLive

    private val compositeDisposable = CompositeDisposable()

    fun getComicsList() {
        comicsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let{ comicList ->
                    comicsListMutableLiveData.postValue(ComicsViewState(false, comicsList = comicList))
                }
            }, { error ->
                error.printStackTrace()
            })
            .apply { compositeDisposable.add(this) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}