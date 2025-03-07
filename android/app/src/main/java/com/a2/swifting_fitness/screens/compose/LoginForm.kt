package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun LoginForm(modifier: Modifier = Modifier, goToHomeScreen: () -> Unit) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    Column(modifier = modifier){
        CustomTextField(
            value = email,
            label = "Email",
            onValueChange = { setEmail(it) }
        ){}
        CustomTextField(
            value = password,
            label = "Password",
            onValueChange = { setPassword(it) }
        ){}

    }
}