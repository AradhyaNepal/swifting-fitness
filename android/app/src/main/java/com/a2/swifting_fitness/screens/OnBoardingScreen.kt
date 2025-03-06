package com.a2.swifting_fitness.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a2.swifting_fitness.view_model.SplashViewModel
import com.a2.swifting_fitness.view_model.SplashViewModelFactory


@Preview
@Composable
private fun Preview() {
    OnBoardingScreen(goToLogin = {

    })
}

@Composable
fun OnBoardingScreen(goToLogin: () -> Unit) {
    val context = LocalContext.current
    val viewModel: SplashViewModel = viewModel(factory = SplashViewModelFactory(context))
    Text(text = "This is on boarding")
    Button(onClick = {
        viewModel.setNotFirstTime()
        goToLogin()
    }) {
        Text("Go to Login")
    }

}