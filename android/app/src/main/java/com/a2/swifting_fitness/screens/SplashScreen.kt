package com.a2.swifting_fitness.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.view_model.SplashViewModel
import com.a2.swifting_fitness.view_model.SplashViewModelFactory
import kotlinx.coroutines.delay


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SplashScreen (
        goToLoginScreen = {
            println("Going to login")
        },
        goToOnBoardingScreen = {
            println("Going to on boarding")
        }
    )
}

@Composable
fun SplashScreen(goToLoginScreen: () -> Unit, goToOnBoardingScreen: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "angle"
    )
    val context = LocalContext.current
    val viewModel: SplashViewModel = viewModel(factory = SplashViewModelFactory(context))

     LaunchedEffect(Unit) {
        delay(2500)
        viewModel.isFirstTime.collect{
            firstTime->
            if(firstTime){
                goToOnBoardingScreen()
            }else{
                goToLoginScreen()
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color(0xFFFF0000))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "Logo",
            colorFilter = ColorFilter.tint(Color(0xFF0000FF)),
            modifier=Modifier.rotate(angle.value)

            )
    }
}