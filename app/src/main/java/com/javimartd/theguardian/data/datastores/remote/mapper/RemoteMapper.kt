package com.javimartd.theguardian.data.datastores.remote.mapper

interface RemoteMapper<R, M> {
    fun mapFromRemote(remoteModel: R): M
}