package com.a2.swifting_fitness.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview



@Preview
@Composable
private fun Preview() {
    LoginScreen(goToForgetPasswordScreen = {}, goToRegisterScreen = {}, goToHomeScreen = {})
}

@Composable
fun LoginScreen(
    goToForgetPasswordScreen: () -> Unit,
    goToRegisterScreen: () -> Unit,
    goToHomeScreen: () -> Unit,
) {
    Text(text = "This is on login screen")
}