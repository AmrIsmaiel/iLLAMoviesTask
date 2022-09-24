package com.som3a.iLLATask.application.di

import com.som3a.common.Mapper
import com.som3a.data.mapper.MoviesDataDomainMapper
import com.som3a.data.model.MovieDTO
import com.som3a.domain.entity.MovieEntity
import com.som3a.feature.mapper.MoviesItemUiMapper
import com.som3a.feature.model.MoviesItemUiModel
import com.som3a.local.mapper.MovieLocalDataMapper
import com.som3a.local.model.MovieLocal
import com.som3a.remote.mapper.MoviesRemoteMapper
import com.som3a.remote.model.MovieRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsMoviesRemoteMapper(mapper: MoviesRemoteMapper): Mapper<MovieRemote, MovieEntity>

    @Binds
    abstract fun bindsMoviesItemUiMapper(mapper: MoviesItemUiMapper): Mapper<MovieEntity, MoviesItemUiModel>

    @Binds
    abstract fun bindsMovieLocalDataMapper(mapper: MovieLocalDataMapper): Mapper<MovieLocal, MovieDTO>

    @Binds
    abstract fun bindsMovieDataDomainMapper(mapper: MoviesDataDomainMapper): Mapper<MovieDTO, MovieEntity>

}