package com.som3a.data.repository

import com.som3a.common.Resource
import com.som3a.data.mapper.MoviesDataDomainMapper
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.entity.ResultWrapper
import com.som3a.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: MoviesDataDomainMapper
) : Repository {

    override suspend fun getRemoteMoviesList(
        page: Int
    ): Flow<Resource<ResultWrapper<MovieEntity>>> {
        return flow {
            val data = remoteDataSource.getMoviesList(page)
            val local = localDataSource.getFavoriteMoviesItems()
            val localResult = mapper.mapList(local)
            if (localResult.isNotEmpty()) {
                localResult.forEach { localMovie ->
                    data.results?.forEach { movieEntity ->
                        if (localMovie.id == movieEntity.id) {
                            movieEntity.apply {
                                isLiked = localMovie.isLiked
                            }
                        }
                    }
                }
            }
            emit(Resource.Success(data))
        }
    }

    override suspend fun getLocalMoviesList(): Flow<Resource<List<MovieEntity>>> {
        return flow {
            try {
                val local = localDataSource.getFavoriteMoviesItems()
                val result = local.map {
                    mapper.map(it)
                }
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun addMovieToFavorite(movie: MovieEntity): Flow<Resource<Long>> {
        return flow {
            try {
                val result = localDataSource.addItem(mapper.reversMap(movie))
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun removeMovieFromFavorite(movie: MovieEntity): Flow<Resource<Int>> {
        return flow {
            try {
                val result = localDataSource.deleteItem(mapper.reversMap(movie))
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}