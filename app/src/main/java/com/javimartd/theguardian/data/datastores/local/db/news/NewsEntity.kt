package com.javimartd.theguardian.data.datastores.local.db.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity (
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "sectionId") var sectionId: String,
        @ColumnInfo(name = "sectionName") var sectionName: String,
        @ColumnInfo(name = "title") var webTitle: String,
        @ColumnInfo(name = "description") var description: String,
        @ColumnInfo(name = "date") var date: String,
        @ColumnInfo(name = "webUrl") var webUrl: String,
        @ColumnInfo(name = "thumbnail") var thumbnail: String
)