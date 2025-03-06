package com.a2.swifting_fitness.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a2.swifting_fitness.data.LocalRepository

class SplashViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(LocalRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}