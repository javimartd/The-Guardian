package com.javimartd.theguardian.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.common.state.Resource
import com.javimartd.theguardian.ui.extensions.toPresentation
import com.javimartd.theguardian.ui.news.adapter.visitor.Visitable
import com.javimartd.theguardian.ui.news.model.AdView
import com.javimartd.theguardian.ui.news.model.HeaderView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _newsObservable: MutableLiveData<Resource<List<Visitable>>> = MutableLiveData()
    val newsObservable: LiveData<Resource<List<Visitable>>>
        get() = _newsObservable

    override fun onCleared() {
        super.onCleared()
        getNewsUseCase.dispose()
    }

    fun fetchNews() {
        _newsObservable.value = Resource.Loading()
        getNewsUseCase.execute(NewsSubscriber())
    }

    inner class NewsSubscriber: DisposableSingleObserver<List<News>>() {
        override fun onError(e: Throwable) {
            _newsObservable.value = Resource.Error(e.message?: "", e)
        }
        override fun onSuccess(t: List<News>) {
            val news = mutableListOf<Visitable>()
            news.addAll(t.toPresentation())
            setHeaderAndAds(news)
            _newsObservable.value = (Resource.Success(news))
        }
    }

    private fun setHeaderAndAds(news: MutableList<Visitable>) {
        news.add(0, HeaderView())
        news.add(3, AdView())
    }
}