package com.som3a.domain.useCase

import com.som3a.common.Resource
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.qualifiers.IoDispatcher
import com.som3a.domain.repository.Repository
import com.som3a.domain.useCase.base.FlowUseCaseNoParam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * class represent the use case of getting the
 * favorite list from the local data base
 */
class GetFavouriteListUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCaseNoParam<List<MovieEntity>>() {

    override suspend fun buildRequest(): Flow<Resource<List<MovieEntity>>> {
        return repository.getLocalMoviesList().flowOn(dispatcher)
    }
}