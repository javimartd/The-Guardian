package com.javimartd.theguardian.data.mapper

interface DataMapper<D, T> {
    fun mapFromData(data: D): T
    fun mapToData(domain: T): D
}