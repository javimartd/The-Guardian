package com.javimartd.theguardian.domain

import com.javimartd.theguardian.domain.entities.News
import com.javimartd.theguardian.domain.repositories.NewsRepository
import com.javimartd.theguardian.domain.usecases.GetNews
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsTest {

    private lateinit var sut: GetNews

    @Mock
    lateinit var repository: NewsRepository

    @Before
    fun setup() {
        initializeUseCase()
    }

    private fun initializeUseCase() {
        sut = GetNews(repository)
    }

    @Test
    @Throws(Exception::class)
    fun `execute get news only one`() {
        runBlocking {  sut.execution() }
        verify(repository, times(1)).getNews()
    }

    @Test
    @Throws(Exception::class)
    fun `get news result is correct`() {
        val products: List<News> = emptyList()
        whenever(repository.getNews()).thenReturn(products)
        val result = runBlocking { sut.execution() }
        Assert.assertEquals(products, result)
    }
}