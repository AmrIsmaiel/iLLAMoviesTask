package com.som3a.domain.entity

/**
 * This data class is to be a wrapper for the movies list.
 * The response of the API has main 4 fields:
 *
 * @param page  -> Int field to indicate the page number we got from the API
 * @param results -> a list to have the movies inside it
 * @param total_pages -> Int field to have total number of pages for the API response
 * @param total_results -> Int field to indicate total number of movies the API has
 *
 * */
data class ResultWrapper<T>(
    val page: Int?,
    val results: List<T>?,
    val total_results: Int?,
    val total_pages: Int?
)