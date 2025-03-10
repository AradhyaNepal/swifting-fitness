package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.screens.compose.AuthUpperCard
import com.a2.swifting_fitness.screens.compose.CustomButton
import com.a2.swifting_fitness.screens.compose.CustomTextField


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RegisterScreen(goToLogin = {}, goToOtpScreen = {})

}

@Composable
fun RegisterScreen(goToLogin: () -> Unit, goToOtpScreen: () -> Unit) {
    val (showError, setShowError) = remember { mutableStateOf(false) }
    val (firstName, setFirstName) = remember { mutableStateOf("") }
    val (lastName, setLastName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = remember { mutableStateOf("") }
    val firstNameErrorMessage=if(firstName.isEmpty())"Please enter first name" else null
    val lastNameErrorMessage=if(lastName.isEmpty())"Missing" else null
    val emailErrorMessage = if (!email.contains("@")) "Please enter last name" else null
    val passwordErrorMessage =
        if (password.length < 8) "Password should be at least 8 characters" else null
    val confirmErrorMessage =
        if (password!=confirmPassword) "Confirm Password must be equal to password" else null
    Column(
        modifier = Modifier
            .background(color = Color(0xFF000000))
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        content =
        {
            AuthUpperCard(
                heading = "Sign Up For Free",
                subHeading = "Getting Started in your fitness journey in just few minutes",
            )
            Column (modifier = Modifier.padding(horizontal = 20.dp)){
                Spacer(Modifier.size(15.dp))
                RegisterText(text="Your Name")
                Spacer(Modifier.size(7.5.dp))
                CustomTextField(
                    value = firstName,
                    label = "First Name",
                    errorIfInvalid = firstNameErrorMessage,
                    showError = showError,
                    onValueChange = { setFirstName(it) }
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "First Name"
                    )
                }
                Spacer(Modifier.size(20.dp))
                CustomTextField(
                    value = lastName,
                    label = "Last Name",
                    errorIfInvalid = lastNameErrorMessage,
                    showError = showError,
                    onValueChange = { setLastName(it) }
                ) {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "Last Name"
                    )
                }
                Spacer(Modifier.size(15.dp))
                RegisterText(text="Email Address")
                Spacer(Modifier.size(7.5.dp))
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
                Spacer(Modifier.size(15.dp))
                RegisterText(text="Password")
                Spacer(Modifier.size(7.5.dp))
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
                Spacer(Modifier.size(15.dp))
                RegisterText(text="Confirm Password")
                Spacer(Modifier.size(7.5.dp))
                CustomTextField(
                    errorIfInvalid = confirmErrorMessage,
                    value = confirmPassword,
                    label = "Confirm Password",
                    isPassword = true,
                    showError = showError,
                    onValueChange = { setConfirmPassword(it) }
                ) {
                    Image(
                        painter = painterResource(R.drawable.monotone_lock_password),
                        contentDescription = "Confirm Password"
                    )
                }
                Spacer(Modifier.size(50.dp))
                CustomButton(
                    title = "Sign Up",
                    onClick = {
                        if (firstNameErrorMessage != null || lastNameErrorMessage != null || emailErrorMessage != null || passwordErrorMessage != null || confirmErrorMessage != null) {
                            setShowError(true)
                        } else {
                            goToOtpScreen()
                        }
                    }
                )
            }


        }

    )
}

@Composable
private fun RegisterText(modifier: Modifier = Modifier, text: String) {
    Text(
        text,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.titleMedium
        )
}