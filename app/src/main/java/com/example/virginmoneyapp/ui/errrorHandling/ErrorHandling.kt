package com.example.virginmoneyapp.ui.errrorHandling

import com.example.virginmoneyapp.data.models.errorHandling.ResponseOfAPI


object ErrorHandling {
    inline fun <reified T> ResponseOfAPI<T>.doIfFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
        if (this is ResponseOfAPI.Failure) {
            callback(message, throwable)
        }
    }

    inline fun <reified T> ResponseOfAPI<T>.doIfSuccess(callback: (value: T) -> Unit) {
        if (this is ResponseOfAPI.Success) {
            callback(value)
        }
    }
}