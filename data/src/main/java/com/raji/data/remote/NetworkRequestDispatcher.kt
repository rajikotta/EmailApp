package com.raji.data.remote

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.core.mapper.ResultMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


suspend fun <T, R> safeCall(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>,
    mapper: ResultMapper<T, R>
): NetworkResult<R, Failure> {

    return withContext(ioDispatcher) {
        runCatching {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(mapper.map(it))
                } ?: NetworkResult.Error(
                    Failure.ServerError(
                        code = response.code(),
                        message = response.message()
                    )
                )
            } else {
                NetworkResult.Error(Failure.ServerError(response.code(), response.message()))
            }
        }
    }.getOrElse {
        it.toNetworkResult()
    }
}