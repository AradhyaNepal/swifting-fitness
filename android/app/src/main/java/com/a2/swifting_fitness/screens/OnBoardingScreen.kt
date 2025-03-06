package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a2.swifting_fitness.R
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
    Box {
        Image(
            painter = painterResource(R.drawable.onboarding3),
            contentDescription = "On Boarding 1",
            )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Welcome To Fitness App!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF)
                )
            Spacer(modifier = Modifier.size(10.0.dp))
            Text("Look good, Feel good!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFFFFF)
                )
            Spacer(modifier = Modifier.size(40.0.dp))
            Button(colors = ButtonDefaults.buttonColors().copy(
                containerColor = androidx.compose.ui.graphics.Color(0xFFF77500),
                contentColor = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
            ), onClick = {
                viewModel.setNotFirstTime()
                goToLogin()
            }) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Get Started",
                    )
                    Spacer(modifier = Modifier.size(7.5.dp))
                    Image(
                        painter = painterResource(R.drawable.monotone_arrow_right),
                        contentDescription = "Arrow",
                    )
                }

            }
            Spacer(modifier = Modifier.size(40.0.dp))
        }
    }


}