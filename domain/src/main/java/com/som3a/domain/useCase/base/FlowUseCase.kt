package com.som3a.domain.useCase.base

import com.som3a.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * This is a base class for the use cases
 * executions.
 * If we have a lot of use cases in the code,
 * it will be more useful to implement it as needed
 * it has @param [Model] as output and [Params] as input
 * for the use case
 */
abstract class FlowUseCase<Model, Params> {

    abstract suspend fun buildRequest(
        params: Params
    ): Flow<Resource<Model>>

    suspend fun execute(type: Params): Flow<Resource<Model>> {
        return try {
            buildRequest(type)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}