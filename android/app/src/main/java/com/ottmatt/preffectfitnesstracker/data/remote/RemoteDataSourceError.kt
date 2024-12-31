package com.ottmatt.preffectfitnesstracker.data.remote

import com.ottmatt.preffectfitnesstracker.data.DataSourceError

sealed class RemoteDataSourceError(message: String) : DataSourceError(message) {
    class RedirectError(message: String) : RemoteDataSourceError(message)
    class ClientError(message: String) : RemoteDataSourceError(message)
    class ServerError(message: String) : RemoteDataSourceError(message)
}
