package com.javimartd.theguardian.data.mapper

interface ModelMapper<M, D> {
    fun mapFromModel(model: M): D
    fun mapToModel(domain: D): M
}