package com.javimartd.theguardian.ui.extensions

import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.ui.news.factory.SectionFactory
import com.javimartd.theguardian.ui.news.model.NewsView

fun List<News>.toPresentation(): List<NewsView> {
    return map { newsToPresentation(it) }
}

private fun newsToPresentation(domain: News): NewsView {
    return NewsView(domain.id,
            SectionFactory.getSection(domain.sectionId, domain.sectionName),
            domain.title,
            domain.date,
            domain.webUrl,
            domain.live,
            domain.thumbnail,
            domain.description)
}