package com.javimartd.theguardian.ui

import com.javimartd.theguardian.domain.entities.News

class NewsFactory {

    companion object Factory {

        fun makeNews(count: Int): List<News> {
            val news = mutableListOf<News>()
            repeat(count) {
                news.add(makeNewsModel())
            }
            return news
        }

        private fun makeNewsModel(): News {
            return News(
                    "film",
                    "Film",
                    "No Time to Die: 25th James Bond film gets a title",
                    "2019-08-20T18:50:53Z",
                    "https://www.theguardian.com/film/2019/aug/20/no-time-to-die-james-bond-25-title",
                    false,
                    "https://www.theguardian.com/film/2019/aug/20/no-time-to-die-james-bond-25-title#img-1",
                    "Latest 007 caper, directed by Beasts of No Nation’s Cary Fukunaga, is set to to be Daniel Craig’s last outing as the superspy")
        }

    }
}