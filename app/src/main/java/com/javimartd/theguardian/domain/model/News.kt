package com.javimartd.theguardian.domain.model

data class News(val sectionId: String,
                val sectionName: String,
                val title: String,
                val date: String,
                val webUrl: String,
                val live: Boolean,
                val thumbnail: String,
                val description: String)