package com.javimartd.theguardian.ui.extensions

import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.ui.news.factory.SectionFactory
import com.javimartd.theguardian.ui.news.model.NewsViewModel

fun List<News>.toPresentation(): List<NewsViewModel> {
    return map { newsToPresentation(it) }
}

private fun newsToPresentation(it: News): NewsViewModel {
    return NewsViewModel(SectionFactory.getSection(it.sectionId, it.sectionName),
            it.title,
            it.date,
            it.webUrl,
            it.live,
            it.thumbnail)
}