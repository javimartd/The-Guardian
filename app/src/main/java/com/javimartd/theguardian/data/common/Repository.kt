package com.javimartd.theguardian.data.common

import retrofit2.Call
import retrofit2.Response

abstract class Repository {

    fun executeCall(call: Call<*>): Response<*> {
        val response = call.execute()
        return if (response == null) {
            throw Exception()
        } else if (!response.isSuccessful) {
            throw Exception()
        } else {
            response
        }
    }
}