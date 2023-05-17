package com.learn.alpha.domain.usecases

import com.learn.alpha.domain.repository.Repository
import javax.inject.Inject

class SomeUseCase @Inject constructor(
	private val repository: Repository,
) {

	suspend operator fun invoke(): Boolean {

		return try {
			repository.doSomething()
		} catch (e: Exception) {
			false
		}
	}

}