package com.javimartd.theguardian.data.datasources.remote

import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.data.datastores.remote.TheGuardianService
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsApiResponse
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsRemoteModel
import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRemoteDataStoreTest {

    lateinit var sut: NewsRemoteDataStore

    @Mock lateinit var theGuardianService: TheGuardianService
    @Mock lateinit var mapper: NewsRemoteMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = NewsRemoteDataStore(theGuardianService, mapper)
    }

    @Test
    fun `get news calls server`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsApiResponse()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        sut.getNewsFromNetwork().test()
        verify(theGuardianService).getNews(any())
    }

    @Test
    fun `get news completed`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsApiResponse()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        val testObserver = sut.getNewsFromNetwork().test()
        testObserver.assertComplete()
    }

    @Test
    fun `get news returns data`() {
        val response = NewsFactory.makeNewsApiResponse()
        val newsDataModel = listOf(NewsFactory.makeNewsDataModel())
        stubTheGuardianService(Single.just(response))
        stubMapper(response.newsResults?.results!!, newsDataModel)
        val testObserver = sut.getNewsFromNetwork().test()
        testObserver.assertValue(newsDataModel)
    }

    @Test
    fun `get news calls with correct parameters`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsApiResponse()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        sut.getNewsFromNetwork().test()
        verify(theGuardianService).getNews("all")
    }

    private fun stubMapper(remote: List<NewsRemoteModel>, data: List<NewsDataModel>) {
        whenever(mapper.mapFromRemote(remote)).thenReturn(data)
    }

    private fun stubTheGuardianService(observable: Single<NewsApiResponse>) {
        whenever(theGuardianService.getNews(any())).thenReturn(observable)
    }
}