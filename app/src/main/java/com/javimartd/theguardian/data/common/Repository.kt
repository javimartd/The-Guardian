package com.javimartd.theguardian.data.common

import com.javimartd.theguardian.data.datasources.api.exceptions.ApiException
import retrofit2.Call
import retrofit2.Response

abstract class Repository {

    fun executeCall(call: Call<*>): Response<*> {
        val response = call.execute()
        return if (response == null) {
            throw Exception()
        } else if (!response.isSuccessful) {
            val errorCode: Int = response.code()
            val message: String = response.message()
            throw ApiException(errorCode, message)
        } else {
            response
        }
    }
}