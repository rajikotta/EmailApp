package com.raji.core.functional

import android.R.attr.value
import com.raji.core.R

sealed class NetworkResult<out T, out R> {
    data class Success<out D>(val value: D) : NetworkResult<D, Nothing>()
    data class Error<out D>(val error: D) : NetworkResult<Nothing, D>()
}


inline fun <T, R, D> NetworkResult<T, R>.fold(
    onSuccess: (T) -> D,
    onError: (R) -> D,
) {
    when (this) {
        is NetworkResult.Success -> onSuccess(value)
        is NetworkResult.Error -> onError(error)
    }
}