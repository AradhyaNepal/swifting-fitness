package com.a2.swifting_fitness

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a2.swifting_fitness.data.DietModel
import com.a2.swifting_fitness.data.SharedViewModel
import com.a2.swifting_fitness.screens.DietDetailScreen
import com.a2.swifting_fitness.screens.ForgetPasswordScreen
import com.a2.swifting_fitness.screens.HomeScreen
import com.a2.swifting_fitness.screens.LoginScreen
import com.a2.swifting_fitness.screens.OnBoardingScreen
import com.a2.swifting_fitness.screens.RegisterOTPScreen
import com.a2.swifting_fitness.screens.RegisterScreen
import com.a2.swifting_fitness.screens.SplashScreen
import com.a2.swifting_fitness.ui.theme.SwiftingFitnessTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftingFitnessTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "splash") {
        composable("splash") {
            SplashScreen(goToLoginScreen = {
                controller.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                    launchSingleTop = true
                }
            }) {
                controller.navigate("onBoarding") {
                    popUpTo("splash") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        composable("onBoarding") {
            OnBoardingScreen {
                controller.navigate("login") {
                    popUpTo("onBoarding") { inclusive = true }
                    launchSingleTop = true

                }
            }
        }
        composable("login") {
            LoginScreen(
                goToForgetPasswordScreen = {
                    controller.navigate("forgetPassword")
                },
                goToRegisterScreen = {
                    controller.navigate("register")
                },
                goToHomeScreen = {
                    controller.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("forgetPassword") {
            ForgetPasswordScreen(goToLogin = {
                controller.navigate("login")
            }) {
                controller.popBackStack()
            }
        }
        composable("home") { entry ->
            val viewModel = hiltViewModel<SharedViewModel>()
            HomeScreen(
                goToDietDetail = {dietModel->
                    viewModel.updateState(dietModel)
                    controller.navigate("diet-detail")
                }
            ) {
                controller.navigate("login")
            }
        }
        composable("diet-detail") {
            val viewModel = hiltViewModel<SharedViewModel>()
            val state = viewModel.sharedState.collectAsStateWithLifecycle()
            val value = state.value
            if(value==null){
                Text(text = "No Details to open screen")
            }else{
                DietDetailScreen(dietModel = value)
            }

        }
        composable("register") {
            RegisterScreen(
                goToLogin = {
                    controller.navigate("login") {
                        popUpTo("register") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                goToOtpScreen = {
                    controller.navigate("register-otp")
                },
            )
        }
        composable("register-otp") {
            RegisterOTPScreen(
                goToRegister = {
                    controller.navigate("register")
                },
                goToHome = {
                    controller.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                },
            ) {
                controller.popBackStack()
            }
        }


    }
}


