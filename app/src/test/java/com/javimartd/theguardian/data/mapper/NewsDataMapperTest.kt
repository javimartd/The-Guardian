package com.javimartd.theguardian.data.mapper

import com.javimartd.theguardian.data.factory.NewsFactory
import com.javimartd.theguardian.data.mapper.news.NewsDataMapper
import com.javimartd.theguardian.data.model.news.NewsDataModel
import com.javimartd.theguardian.domain.model.News
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsDataMapperTest {

    private val mapper = NewsDataMapper()

    @Test
    fun mapFromData() {
        val data = listOf(NewsFactory.makeNewsDataModel())
        val domain = mapper.mapFromData(data)
        assertEqualData(data[0], domain[0])
    }

    private fun assertEqualData(data: NewsDataModel, domain: News) {
        Assert.assertEquals(data.sectionId, domain.sectionId)
        Assert.assertEquals(data.sectionName, domain.sectionName)
        Assert.assertEquals(data.title, domain.title)
        Assert.assertEquals(data.date, domain.date)
        Assert.assertEquals(data.webUrl, domain.webUrl)
        Assert.assertEquals(data.live, domain.live)
        Assert.assertEquals(data.thumbnail, domain.thumbnail)
        Assert.assertEquals(data.description, domain.description)
    }
}