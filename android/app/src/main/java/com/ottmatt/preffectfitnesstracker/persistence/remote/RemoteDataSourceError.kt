package com.ottmatt.preffectfitnesstracker.persistence.remote

import com.ottmatt.preffectfitnesstracker.persistence.DataSourceError

sealed class RemoteDataSourceError(message: String) : DataSourceError(message) {
    class RedirectError(message: String) : RemoteDataSourceError(message)
    class ClientError(message: String) : RemoteDataSourceError(message)
    class ServerError(message: String) : RemoteDataSourceError(message)
}
