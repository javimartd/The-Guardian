package com.javimartd.theguardian.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.javimartd.theguardian.data.factory.DataFactory
import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.common.state.Resource
import com.javimartd.theguardian.ui.news.viewmodel.NewsViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableSingleObserver
import org.hamcrest.CoreMatchers.instanceOf
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
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<News>>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
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
        Assert.assertTrue(sut.newsObservable.value is Resource.Loading)
    }

    @Test
    fun `fetch news returns success`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        val news = NewsFactory.makeNews(10)
        captor.firstValue.onSuccess(news)
        Assert.assertTrue(sut.newsObservable.value is Resource.Success)
    }

    @Test
    fun `fetch news returns data`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        val news = NewsFactory.makeNews(10)
        captor.firstValue.onSuccess(news)
        Assert.assertFalse(sut.newsObservable.value?.data!!.isEmpty())
        //Assert.assertEquals(news.toPresentation(), sut.newsObservable.value?.data)
    }

    @Test
    fun `fetch news returns error`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(""))
        Assert.assertTrue(sut.newsObservable.value is Resource.Error)
    }

    @Test
    fun `fetch news returns connection error`() {
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(UnknownHostException(""))
        Assert.assertThat(sut.newsObservable.value?.error, instanceOf(UnknownHostException::class.java))
    }

    @Test
    fun `fetch news returns error message`() {
        val errorMessage = DataFactory.randomString()
        sut.fetchNews()
        Mockito.verify(getNewsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))
        Assert.assertEquals(errorMessage, sut.newsObservable.value?.message)
    }
}