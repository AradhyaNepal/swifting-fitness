package com.a2.swifting_fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a2.swifting_fitness.screens.ForgetPasswordScreen
import com.a2.swifting_fitness.screens.HomeScreen
import com.a2.swifting_fitness.screens.LoginScreen
import com.a2.swifting_fitness.screens.OnBoardingScreen
import com.a2.swifting_fitness.screens.RegisterOTPScreen
import com.a2.swifting_fitness.screens.RegisterScreen
import com.a2.swifting_fitness.screens.SplashScreen
import com.a2.swifting_fitness.ui.theme.SwiftingFitnessTheme

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
fun MyApp(){
    val controller= rememberNavController()
    NavHost(navController = controller,startDestination="splash"){
        composable("splash"){
            SplashScreen(goToLoginScreen= {
                controller.navigate("login"){
                    popUpTo("splash") { inclusive = true }
                    launchSingleTop = true
                }
            }){
                controller.navigate("onBoarding"){
                    popUpTo("splash") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        composable("onBoarding"){
            OnBoardingScreen{
                controller.navigate("login"){
                    popUpTo("onBoarding") { inclusive = true }
                    launchSingleTop = true

                }
            }
        }
        composable("login"){
            LoginScreen(
                goToForgetPasswordScreen = {
                    controller.navigate("forgetPassword")
                },
                goToRegisterScreen = {
                    controller.navigate("register")
                },
                goToHomeScreen={
                    controller.navigate("home"){
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("forgetPassword"){
            ForgetPasswordScreen(goToLogin = {
                controller.navigate("login")
            }){
                controller.popBackStack()
            }
        }
        composable("home"){
            HomeScreen{
                controller.navigate("login")
            }
        }
        composable("register"){
            RegisterScreen(
                goToLogin={
                    controller.navigate("login"){
                        popUpTo("register") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                goToOtpScreen={
                    controller.navigate("register-otp")
                },
            )
        }
        composable("register-otp"){
            RegisterOTPScreen(
                goToRegister={
                    controller.navigate("register")
                },
                goToHome={
                    controller.navigate("home"){
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                },
            ){
                controller.popBackStack()
            }
        }
    }
}
