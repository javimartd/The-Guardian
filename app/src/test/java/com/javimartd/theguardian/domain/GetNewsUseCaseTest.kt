package com.javimartd.theguardian.domain

import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.domain.executor.PostExecutionThread
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsUseCaseTes {

    lateinit var sut: GetNewsUseCase

    @Mock lateinit var repository: NewsRepository
    @Mock lateinit var postThreadExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = GetNewsUseCase(repository, postThreadExecutionThread)
    }

    @Test
    fun `get news completed`() {
        stubNewsRepository(Single.just(listOf(NewsFactory.makeNews())))
        val testObserver = sut.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun `get news returns data`() {
        val news = listOf(NewsFactory.makeNews())
        stubNewsRepository(Single.just(news))
        val testObserver = sut.buildUseCaseSingle().test()
        testObserver.assertValue(news)
    }

    private fun stubNewsRepository(observable: Single<List<News>>) {
        whenever(repository.getNews()).thenReturn(observable)
    }
}