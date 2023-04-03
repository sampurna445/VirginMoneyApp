package com.example.virginmoneyapp.data.models.errorHandling

sealed class ResponseOfAPI<out T>  {
    data class Success<out R>(val value: R): ResponseOfAPI<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ResponseOfAPI<Nothing>()
}