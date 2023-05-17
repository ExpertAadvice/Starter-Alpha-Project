package com.learn.alpha.di

import com.learn.alpha.data.data_source.remote.ApiService
import com.learn.alpha.data.repository.RepositoryImpl
import com.learn.alpha.domain.repository.Repository
import com.learn.alpha.domain.usecases.SomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun providesApiService(): ApiService {
		return ApiService.service
	}

	@Provides
	@Singleton
	fun providesRepository(apiService: ApiService): Repository {
		return RepositoryImpl(apiService)
	}

	@Provides
	@Singleton
	fun providesUseCase(repository: Repository) : SomeUseCase {
		return SomeUseCase(repository)
	}

}