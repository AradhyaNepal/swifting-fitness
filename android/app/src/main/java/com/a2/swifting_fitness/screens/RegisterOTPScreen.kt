package com.a2.swifting_fitness.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.screens.compose.CustomButton
import com.a2.swifting_fitness.screens.compose.OTPInputTextFields

@Composable
fun RegisterOTPScreen(goToRegister: () -> Unit, goToHome: () -> Unit){
    val (otpValue, setOTPValue) = remember { mutableStateOf(List(6) { "" }) }
    val (otpCompleted, setOtpCompleted) = remember { mutableStateOf(false) }
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
                onPressed = {},
                description = "Previous Screen",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Spacer(Modifier.size(25.dp))
            Text(
                text = "Verification Code",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.size(15.dp))
            Text(
                text = "We have sent the verification code to your email address",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray,
                fontWeight = FontWeight.SemiBold
            )
                Spacer(Modifier.size(20.dp))
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
        CustomButton(title = "Confirm", disabled = !otpCompleted) {

        }
        Spacer(Modifier.size(20.dp))
        }



}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RegisterOTPScreen(goToRegister = {}, goToHome = {})

}