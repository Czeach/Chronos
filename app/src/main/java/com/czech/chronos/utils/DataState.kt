package com.czech.chronos.utils

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean,
    val isError: Boolean
) {
    companion object {
        fun <T> error(
            message: String
        ): DataState<T> {
            return DataState(
                message = message,
                data = null,
                isError = true,
                isSuccess = false
            )
        }

        fun <T> success(
            message: String? = null,
            data: T? = null,
            success: Boolean = true
        ): DataState<T> {
            return DataState(
                message = message,
                data = data,
                isError = false,
                isSuccess = success
            )
        }

        fun <T> loading() = DataState<T>(
            isLoading = true,
            isError = false,
            isSuccess = false,
        )
    }
}