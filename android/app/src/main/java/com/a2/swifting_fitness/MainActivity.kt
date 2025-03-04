package com.a2.swifting_fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable("splash"){}
        composable("onBoarding"){}
        composable("login"){}
        composable("forgetPassword"){}
        composable("home"){}
    }
}
