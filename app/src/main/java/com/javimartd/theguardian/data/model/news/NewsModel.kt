package com.javimartd.theguardian.data.model.news

data class NewsModel(val sectionId: String,
                val sectionName: String,
                val title: String,
                val date: String,
                val webUrl: String,
                val live: Boolean,
                val thumbnail: String,
                val description: String)