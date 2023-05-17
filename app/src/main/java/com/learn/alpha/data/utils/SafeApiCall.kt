package com.learn.alpha.data.utils

import com.learn.alpha.utils.InvalidException
import retrofit2.HttpException

interface SafeApiCall {

	suspend fun <T> safeApiCall(apiCall: suspend () -> T): T {

		try {
			return apiCall.invoke()
		} catch (t: Throwable) {
			when (t) {
				is HttpException -> {
					throw InvalidException("${t.code()} ->  ${t.response()?.errorBody()?.string().toString()}")
				}

				else -> {
					throw InvalidException(t.localizedMessage ?: "")
				}
			}
		}
	}
}