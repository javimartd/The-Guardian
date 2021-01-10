package com.javimartd.theguardian.data

import com.javimartd.theguardian.data.datastores.NewsDataStore
import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.data.mapper.news.NewsDataMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsRepositoryTest {

    private lateinit var sut: NewsRepositoryImpl

    private val remoteDataStore = mock<NewsDataStore>()
    private val localDataStore = mock<NewsDataStore>()
    private val memoryDataStore = mock<NewsDataStore>()
    private val mapper = mock<NewsDataMapper>()

    @Before
    fun setup() {
        sut = NewsRepositoryImpl(remoteDataStore, memoryDataStore, localDataStore, mapper)
    }

    @Test
    fun `get news completed`() {
        stubNewsDataStore(Single.just(listOf(NewsFactory.makeNewsDataModel())))
        stubMapper(any(), listOf(NewsFactory.makeNews()))
        val testObserver = sut.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun `get news returns data`() {
        val newsDataModel = listOf(NewsFactory.makeNewsDataModel())
        val news = listOf(NewsFactory.makeNews())
        stubNewsDataStore(Single.just(newsDataModel))
        stubMapper(newsDataModel, news)
        val testObserver = sut.getNews().test()
        testObserver.assertValue(news)
    }

    private fun stubMapper(data: List<NewsDataModel>, domain: List<News>) {
        whenever(mapper.mapFromData(data)).thenReturn(domain)
    }

    private fun stubNewsDataStore(observable: Single<List<NewsDataModel>>) {
        whenever(remoteDataStore.getNewsFromNetwork()).thenReturn(observable)
    }
}