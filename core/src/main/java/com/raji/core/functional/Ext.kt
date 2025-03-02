package com.raji.core.functional

import com.raji.core.R

fun Boolean?.orDefault(): Boolean = this ?: false



fun <T,R : Any> List<T>?.mapOrDefault(defaultListValue : List<R> = emptyList(), transform: (T) -> R): List<R> {
    return this?.filterNotNull()?.map(transform) ?: defaultListValue
}