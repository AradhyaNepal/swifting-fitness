package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        LoginForm { }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier, goToHomeScreen: () -> Unit) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (showError, setShowError) = remember { mutableStateOf(false) }
    val emailErrorMessage = if (!email.contains("@")) "Please enter valid email" else null;
    val passwordErrorMessage =
        if (password.length < 8) "Password should be at least 8 characters" else null;
    Column(modifier = modifier) {
        CustomTextField(
            value = email,
            label = "Email",
            errorIfInvalid = emailErrorMessage,
            showError = showError,
            onValueChange = { setEmail(it) }
        ) {
            Image(
                painter = painterResource(R.drawable.monotone_email),
                contentDescription = "Email",
            )
        }
        Spacer(Modifier.size(20.dp))
        CustomTextField(
            errorIfInvalid = passwordErrorMessage,
            value = password,
            label = "Password",
            isPassword = true,
            showError = showError,
            onValueChange = { setPassword(it) }
        ) {
            Image(
                painter = painterResource(R.drawable.monotone_lock_password),
                contentDescription = "Password"
            )
        }
        Spacer(Modifier.size(20.dp))
        CustomButton(
            title = "Login",
            onClick = {
                if (emailErrorMessage != null || passwordErrorMessage != null) {
                    setShowError(true)
                } else {
                   goToHomeScreen()
                }
            }
        )

    }
}