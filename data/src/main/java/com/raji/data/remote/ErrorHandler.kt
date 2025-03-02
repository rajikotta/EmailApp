package com.raji.data.remote

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkResult(): NetworkResult.Error<Failure> {
    return when (this) {
        is IOException -> NetworkResult.Error(Failure.NetworkError(this))
        is HttpException -> {
            val code = code()
            val message = message()
            NetworkResult.Error(Failure.ServerError(code, message))
        }

        else -> NetworkResult.Error(Failure.UnknownError(this))
    }

}