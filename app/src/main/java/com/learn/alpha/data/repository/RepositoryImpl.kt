package com.learn.alpha.data.repository

import com.learn.alpha.data.data_source.remote.ApiService
import com.learn.alpha.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
	private val api: ApiService,
) : Repository {

	override suspend fun doSomething(): Boolean {
		return try {
			api.doSomething()
			true

		} catch (e: Exception) {
			false
		}
	}
}