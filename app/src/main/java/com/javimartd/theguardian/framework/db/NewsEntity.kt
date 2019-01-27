package com.javimartd.theguardian.framework.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity (
        @PrimaryKey(autoGenerate = true) var uid: Int,
        @ColumnInfo(name = "section") var sectionName: String?,
        @ColumnInfo(name = "title") var webTitle: String?,
        @ColumnInfo(name = "date") var date: String?,
        @ColumnInfo(name = "webUrl") var webUrl: String?,
        @ColumnInfo(name = "thumbnail") var thumbnail: String?
)