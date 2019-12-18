package com.javimartd.theguardian.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.javimartd.theguardian.data.factory.DataFactory
import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.extensions.toPresentation
import com.javimartd.theguardian.ui.news.state.Status
import com.javimartd.theguardian.ui.news.viewmodel.NewsViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException


@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var getNewsUseCase: GetNewsUseCase

    private lateinit var sut: NewsViewModel
    private lateinit var captor: KArgumentCaptor<DisposableObserver<List<News>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        captor = argumentCaptor()
        sut = NewsViewModel(getNewsUseCase)
    }

    @Test
    fun `fetch news executes use case`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun `fetch news returns loading`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        Assert.assertEquals(Status.LOADING, sut.getNewsObservable().value?.status)
    }

    @Test
    fun `fetch news returns success`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        val news = listOf(NewsFactory.makeNews())
        captor.firstValue.onNext(news)
        Assert.assertEquals(Status.SUCCESS, sut.getNewsObservable().value?.status)
    }

    @Test
    fun `fetch news returns no data`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        val news = emptyList<News>()
        captor.firstValue.onNext(news)
        Assert.assertEquals(Status.NO_DATA, sut.getNewsObservable().value?.status)
    }

    @Test
    fun `fetch news returns data`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        val news = listOf(NewsFactory.makeNews())
        captor.firstValue.onNext(news)
        Assert.assertEquals(news.toPresentation(), sut.getNewsObservable().value?.data)
    }

    @Test
    fun `fetch news returns error`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        Assert.assertEquals(Status.ERROR, sut.getNewsObservable().value?.status)
    }

    @Test
    fun `fetch news returns connection error`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(UnknownHostException())
        Assert.assertEquals(Status.CONNECTION_ERROR, sut.getNewsObservable().value?.status)
    }

    @Test
    fun `fetch news returns error message`() {
        val errorMessage = DataFactory.randomString()
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))
        Assert.assertEquals(errorMessage, sut.getNewsObservable().value?.message)
    }
}