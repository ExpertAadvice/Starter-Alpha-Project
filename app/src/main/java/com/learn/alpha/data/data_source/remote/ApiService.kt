package com.learn.alpha.data.data_source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

	@GET("/home")
	suspend fun doSomething()

	companion object {

		private const val BASE_URL = "https://learnKotlin.com/"

		private val interceptor = HttpLoggingInterceptor().apply {
			setLevel(HttpLoggingInterceptor.Level.BASIC)
		}

		private val client = OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build()

		val service: ApiService = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiService::class.java)
	}

}
