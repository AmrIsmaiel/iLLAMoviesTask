package com.som3a.iLLATask.application.di

import com.som3a.data.repository.LocalDataSource
import com.som3a.data.repository.RemoteDataSource
import com.som3a.data.repository.RepositoryImp
import com.som3a.domain.repository.Repository
import com.som3a.local.source.LocalDataSourceImp
import com.som3a.remote.source.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImp): LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun provideRepository(repository: RepositoryImp): Repository

}