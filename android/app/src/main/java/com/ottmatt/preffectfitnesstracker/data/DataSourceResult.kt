package com.ottmatt.preffectfitnesstracker.data

/**
 * Generic class for retrieving data or propagating errors from any DataSource.
 */
sealed class DataSourceResult<T>(
    val data: T? = null,
    val error: DataSourceError? = null
) {
    class Success<T>(data: T) : DataSourceResult<T>(data = data)
    class Error<T>(error: DataSourceError) : DataSourceResult<T>(error = error)
}

open class DataSourceError(val message: String) {
    class GenericError(message: String) : DataSourceError(message)
}
