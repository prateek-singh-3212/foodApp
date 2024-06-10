package com.example.math.utils

sealed class Result<T>() {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val exception: String) : Result<T>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun <T> error(ex: String) = Error<T>(ex)
    }
}