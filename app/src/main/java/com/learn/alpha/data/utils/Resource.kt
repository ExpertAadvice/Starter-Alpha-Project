package com.learn.alpha.data.utils

sealed class Resource<out T> {

	data class Success<out T>(val value: T) : Resource<T>()

	data class Failure(
		val errorCode: Int = 0,
		val errorMsg: String = "",
	) : Resource<Nothing>()

	object Loading : Resource<Nothing>()
}
