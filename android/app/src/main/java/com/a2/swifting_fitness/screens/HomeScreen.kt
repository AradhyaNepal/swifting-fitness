package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.screens.compose.CustomAlertDialog
import com.a2.swifting_fitness.screens.compose.CustomButton
import com.a2.swifting_fitness.screens.compose.DietCard
import com.a2.swifting_fitness.screens.compose.HomeUpperCard
import com.a2.swifting_fitness.screens.compose.WorkoutCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen {

    }

}

@Composable
fun HomeScreen(goToLogin: () -> Unit) {
    val (openDialog, setOpenDialog) = remember { mutableStateOf(false) }
    when {
        openDialog -> CustomAlertDialog(
            onDismissRequest = {
                setOpenDialog(false)
            },
            onConfirmation = {
                setOpenDialog(false)
                goToLogin()
            },
            dialogText = "Do you really want to logout",
            dialogTitle = "Logout!",
            icon = Icons.Filled.Warning
        )
    }
    Column(
        modifier = Modifier
            .background(Color(0xFF000000))
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.size(60.dp))
        HomeUpperCard()
        Spacer(Modifier.size(20.dp))
        HorizontalDivider()
        Spacer(Modifier.size(40.dp))
        WorkoutCard()
        Spacer(Modifier.size(30.dp))
        DietCard()
        CustomButton(title = "Logout!") {
            setOpenDialog(true)
        }
    }

}