package com.ighorosipov.pizzaapp.utils

sealed class Result<T: Any>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T: Any>(data: T): Result<T>(data)
    class Error<T: Any>(data: T? = null, message: String?): Result<T>(data, message)
    class Loading<T : Any> : Result<T>()
}