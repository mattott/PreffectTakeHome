package com.ottmatt.preffectfitnesstracker.persistence

/**
 * Generic class for retrieving data or propagating errors from any DataSource.
 */
sealed class DataSourceResult<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : DataSourceResult<T>(data)

    // Could pass an error message here if we cared about propagating the error message to the user.
    class Error<T> : DataSourceResult<T>()
}
