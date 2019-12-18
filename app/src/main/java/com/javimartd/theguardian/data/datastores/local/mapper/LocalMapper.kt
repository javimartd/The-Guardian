package com.javimartd.theguardian.data.datastores.local.mapper

interface LocalMapper<E, D> {
    fun mapFromLocal(entity: E): D
    fun mapToLocal(data: D): E
}