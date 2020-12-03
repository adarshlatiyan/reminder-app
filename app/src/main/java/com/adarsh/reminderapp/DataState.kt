package com.adarsh.reminderapp

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}