package com.a2.swifting_fitness.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.a2.swifting_fitness.screens.compose.LoginAlternativeOptions
import com.a2.swifting_fitness.screens.compose.LoginForm
import com.a2.swifting_fitness.screens.compose.LoginUpperCard


@Preview(showSystemUi = true, showBackground = true)
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
    Column{
        LoginUpperCard()
        LoginForm(goToHomeScreen={
            goToHomeScreen()
        })
        LoginAlternativeOptions(
            goToRegisterScreen={
                goToRegisterScreen()
            },
            goToForgetPasswordScreen={
                goToForgetPasswordScreen()
            }
        )
    }
}




