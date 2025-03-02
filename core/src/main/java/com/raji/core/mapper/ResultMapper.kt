package com.raji.core.mapper

interface ResultMapper<T, R> {
    fun map(input: T): R
}