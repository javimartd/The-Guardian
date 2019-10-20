package com.javimartd.theguardian.data.datastores.local.mapper

interface LocalMapper<E, M> {
    fun mapFromLocal(entity: E): M
    fun mapToLocal(model: M): E
}