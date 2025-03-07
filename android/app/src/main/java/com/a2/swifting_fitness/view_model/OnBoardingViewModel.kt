package com.a2.swifting_fitness.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.data.LocalRepository
import com.a2.swifting_fitness.data.OnBoardingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OnBoardingViewModel() : ViewModel() {
    val onBoardingModel = listOf(
        OnBoardingModel(
            title = "Groceries at Your Fingertips",
            description = "Find the favorites your store your want by your locations on neighborhood.",
            imageRes = R.drawable.onboarding2
        ),
        OnBoardingModel(
            title = "Fresh Delivered, Hassle Free",
            description = "Find the favorites your store your want by your locations on neighborhood.",
            imageRes = R.drawable.onboarding3
        ),
        OnBoardingModel(
            title = "Shop Smart. Eat Fresh",
            description = "Find the favorites your store your want by your locations on neighborhood.",
            imageRes = R.drawable.onboarding4
        )
    )
}