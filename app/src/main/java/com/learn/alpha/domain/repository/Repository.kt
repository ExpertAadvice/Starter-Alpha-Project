package com.learn.alpha.domain.repository

interface Repository {

	suspend fun doSomething() : Boolean

}