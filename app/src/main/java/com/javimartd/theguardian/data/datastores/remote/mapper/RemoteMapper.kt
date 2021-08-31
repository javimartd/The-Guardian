package com.javimartd.theguardian.data.datastores.remote.mapper

interface RemoteMapper<R, D> {
    fun mapFromRemote(remote: R?): D
}