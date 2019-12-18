package com.javimartd.theguardian.ui.news

import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.extensions.toPresentation
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class NewsPresenterTest {

    private lateinit var sut: NewsPresenter
    private lateinit var captor: KArgumentCaptor<DisposableObserver<List<News>>>

    @Mock private lateinit var getNewsUseCase: GetNewsUseCase
    @Mock private lateinit var view: NewsContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        captor = argumentCaptor()
        sut = NewsPresenter(view, getNewsUseCase)
    }

    @Test
    fun `get news and show news`() {
        sut.getNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(listOf(NewsFactory.makeNews()))
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showNews(any())
    }

    @Test
    fun `get news and show empty state`() {
        sut.getNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(emptyList())
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showEmptyState()
    }

    @Test
    fun `get news and show news with correct data`() {
        val news = listOf(NewsFactory.makeNews())
        sut.getNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(news)
        Mockito.verify(view).showNews(news.toPresentation())
    }

    @Test
    fun `get news and show error`() {
        sut.getNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showError()
    }

    @Test
    fun `get news and show connection error`() {
        sut.getNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(UnknownHostException())
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showConnectionError()
    }
}