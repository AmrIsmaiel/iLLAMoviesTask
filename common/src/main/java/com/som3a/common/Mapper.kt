package com.som3a.common


/**
 * This interface is a base for all mappers in the app.
 * here we identify all functions we use in every mapper
 * */
interface Mapper<I, O> {

    fun map(input: I): O

    fun reversMap(input: O): I

    fun mapList(input: List<I>): List<O> {
        return input.map { i -> map(i) }
    }
}
