package com.czech.chronos.utils

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
    val success: Boolean = false
) {
    companion object {
        fun <T> error(
            message: String
        ): DataState<T> {
            return DataState(
                message = message,
                data = null,
                success = false
            )
        }

        fun <T> data(
            message: String? = null,
            data: T? = null,
            success: Boolean = true
        ): DataState<T> {
            return DataState(
                message = message,
                data = data,
                success = success
            )
        }

        fun <T> loading() = DataState<T>(
            isLoading = true
        )
    }
}