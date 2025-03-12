package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.a2.swifting_fitness.screens.compose.CustomButton
import com.a2.swifting_fitness.screens.compose.CustomTextField
import com.a2.swifting_fitness.screens.compose.OTPInputTextFields


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    ForgetPasswordScreen(goToLogin = {}, goBack = {})

}

@Composable
fun ForgetPasswordScreen(goToLogin: () -> Unit,goBack:()->Unit) {
    val (otpValue, setOTPValue) = remember { mutableStateOf(List(6) { "" }) }
    val (otpSent, setOTPSent) = remember { mutableStateOf(false) }
    val (otpCompleted, setOtpCompleted) = remember { mutableStateOf(false) }
    val (showError, setShowError) = remember { mutableStateOf(false) }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val emailErrorMessage = if (!email.contains("@")) "Please enter valid email" else null
    val passwordErrorMessage =
        if (password.length < 8) "Password should be at least 8 characters" else null
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(Modifier.size(30.dp))
            NextPreviousButton(
                resources = R.drawable.back_arrow,
                onPressed = goBack,
                description = "Previous Screen",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Spacer(Modifier.size(25.dp))
            Text(
                text = "Reset Password",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.size(30.dp))
            CustomTextField(
                enabled = !otpSent,
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
            if (otpSent) {
                Spacer(Modifier.size(40.dp))
                OTPInputTextFields(
                    otpValues = otpValue,
                    otpLength = 6,
                    onUpdateOtpValuesByIndex = { index, value ->
                        val newOtpValue = otpValue.toMutableList()
                        newOtpValue[index] = value
                        setOTPValue(newOtpValue)
                        if(value.isEmpty()&&otpCompleted){
                            setOtpCompleted(false)
                        }
                    },
                    onOtpInputComplete = {
                        setOtpCompleted(true)
                    },

                    )
            }
        }

        CustomButton(title = if (otpSent) "Verify OTP" else "Send OTP", disabled = otpSent&&!otpCompleted) {
            if (otpSent) {
                println(otpValue)
            } else {
                if (emailErrorMessage != null || passwordErrorMessage != null) {
                    setShowError(true)
                } else {
                    println("OTP is sent")
                    setOTPSent(true)
                }
            }
        }
        Spacer(Modifier.size(20.dp))
    }

}
