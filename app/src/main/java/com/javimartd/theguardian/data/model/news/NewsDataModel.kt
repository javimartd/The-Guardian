package com.javimartd.theguardian.data.model.news

data class NewsDataModel(val id: String,
                         val sectionId: String,
                         val sectionName: String,
                         val title: String,
                         val date: String,
                         val webUrl: String,
                         val live: Boolean,
                         val thumbnail: String,
                         val description: String)