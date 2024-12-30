package com.ottmatt.preffectfitnesstracker.persistence

/**
 * Generic class for retrieving data or propagating errors from any API.
 */
sealed class ApiResult<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : ApiResult<T>(data)

    // Could pass an error message here if we cared about propagating the error message to the user.
    class Error<T> : ApiResult<T>()
}
