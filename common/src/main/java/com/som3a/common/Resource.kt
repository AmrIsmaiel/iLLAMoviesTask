package com.som3a.common


/**
 * This sealed class is handle all response cases we can get after threading
 * */
sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()
}