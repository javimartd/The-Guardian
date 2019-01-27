package com.javimartd.theguardian.ui

import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNews
import com.javimartd.theguardian.ui.news.NewsPresenter
import com.javimartd.theguardian.ui.news.NewsView
import com.javimartd.theguardian.ui.news.model.NewsViewModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsPresenterTest {

    private lateinit var sut: NewsPresenter

    @Mock
    private lateinit var getNews: GetNews

    @Mock
    private lateinit var newsView: NewsView

    @Before
    fun setUp() {
        sut = NewsPresenter(getNews)
    }

    @Test
    fun `execute get news use case only once`() {
        sut.attachView(newsView)
        verify(getNews, times(1)).execute(any(), any(), any())
    }

    @Test
    fun `when get news then show loading`() {
        sut.attachView(newsView)
        then(newsView).should().showLoading()
    }

    @Test
    fun `after execute get news then hide loading`() {
        val onCompleteCaptor = argumentCaptor<(Any) -> Unit>()
        val result: List<News> = emptyList()
        sut.attachView(newsView)
        verify(getNews).execute(onCompleteCaptor.capture(), any(), any())
        onCompleteCaptor.firstValue.invoke(result)
        verify(newsView).hideLoading()
    }

    @Test
    fun `after execute get news then show news`() {
        val onCompleteCaptor = argumentCaptor<(Any) -> Unit>()
        val result: List<NewsViewModel> = emptyList()
        sut.attachView(newsView)
        verify(getNews).execute(onCompleteCaptor.capture(), any(), any())
        onCompleteCaptor.firstValue.invoke(result)
        verify(newsView).showNews(result)
    }
}