package com.som3a.domain.useCase

import com.som3a.common.Resource
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.qualifiers.IoDispatcher
import com.som3a.domain.repository.Repository
import com.som3a.domain.useCase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Long, MovieEntity>() {

    override suspend fun buildRequest(params: MovieEntity): Flow<Resource<Long>> {
        return repository.addMovieToFavorite(params).flowOn(dispatcher)
    }
}