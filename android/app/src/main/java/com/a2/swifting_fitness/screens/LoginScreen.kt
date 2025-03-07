package com.a2.swifting_fitness.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .background(color = Color(0xFF000000))
            .fillMaxSize().verticalScroll(rememberScrollState()),
        content =
            {
                LoginUpperCard()
                Column (
                    modifier = Modifier.padding(horizontal = 20.0.dp, vertical = 30.0.dp)
                ){
                    LoginForm(goToHomeScreen = {
                        goToHomeScreen()
                    })
                    Spacer(modifier = Modifier.size(45.dp))
                    LoginAlternativeOptions(
                        goToRegisterScreen = {
                            goToRegisterScreen()
                        },
                        goToForgetPasswordScreen = {
                            goToForgetPasswordScreen()
                        }
                    )
                }
            }

        )
}




