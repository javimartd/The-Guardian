package com.javimartd.theguardian.data.datasources.remote

import com.javimartd.theguardian.BuildConfig
import com.javimartd.theguardian.data.datastores.remote.NewsRemoteDataStore
import com.javimartd.theguardian.data.datastores.remote.TheGuardianService
import com.javimartd.theguardian.data.datastores.remote.mapper.news.NewsRemoteMapper
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsRemoteModel
import com.javimartd.theguardian.data.datastores.remote.model.news.NewsResponseModel
import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
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
        MockitoAnnotations.initMocks(this)
        sut = NewsRemoteDataStore(theGuardianService, mapper)
    }

    @Test
    fun `get news calls server`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsResponseModel()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        sut.getNews().test()
        verify(theGuardianService).getNews(any(), any())
    }

    @Test
    fun `get news completed`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsResponseModel()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        val testObserver = sut.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun `get news returns data`() {
        val response = NewsFactory.makeNewsResponseModel()
        val newsDataModel = listOf(NewsFactory.makeNewsDataModel())
        stubTheGuardianService(Single.just(response))
        stubMapper(response.newsResponse.results, newsDataModel)
        val testObserver = sut.getNews().test()
        testObserver.assertValue(newsDataModel)
    }

    @Test
    fun `get news calls with correct parameters`() {
        stubTheGuardianService(Single.just(NewsFactory.makeNewsResponseModel()))
        stubMapper(any(), listOf(NewsFactory.makeNewsDataModel()))
        sut.getNews().test()
        verify(theGuardianService).getNews("all", BuildConfig.THE_GUARDIAN_API_KEY)
    }

    private fun stubMapper(remote: List<NewsRemoteModel>, data: List<NewsDataModel>) {
        whenever(mapper.mapFromRemote(remote)).thenReturn(data)
    }

    private fun stubTheGuardianService(observable: Single<NewsResponseModel>) {
        whenever(theGuardianService.getNews(any(), any())).thenReturn(observable)
    }
}