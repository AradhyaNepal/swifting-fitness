package com.a2.swifting_fitness.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a2.swifting_fitness.data.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: LocalRepository) : ViewModel() {
    val isFirstTime: Flow<Boolean> = repository.isFirstTime

    fun setNotFirstTime() {
        viewModelScope.launch {
            repository.setNotFirstTime()
        }
    }
}