package com.learn.alpha.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.alpha.domain.usecases.SomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val someUseCase: SomeUseCase,
) : ViewModel() {

	private val _homeEvent = Channel<Boolean>()
	val homeEvent = _homeEvent.receiveAsFlow()

	fun doSomething() {

		viewModelScope.launch {

			try {
				val result = someUseCase.invoke()
				_homeEvent.send(result)
			} catch (e: Exception) {
				_homeEvent.send(false)
			}
		}
	}
}