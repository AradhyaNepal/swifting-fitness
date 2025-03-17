package com.a2.swifting_fitness.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _sharedState = MutableStateFlow<DietModel?>(value = null)
    val sharedState = _sharedState.asStateFlow()

    fun updateState(dietModel: DietModel) {
        _sharedState.value = dietModel
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}