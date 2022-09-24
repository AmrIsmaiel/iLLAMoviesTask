package com.som3a.domain.useCase.base

import com.som3a.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class FlowUseCaseNoParam<Model> {

    abstract suspend fun buildRequest(): Flow<Resource<Model>>

    suspend fun execute(): Flow<Resource<Model>> {
        return try {
            buildRequest()
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}