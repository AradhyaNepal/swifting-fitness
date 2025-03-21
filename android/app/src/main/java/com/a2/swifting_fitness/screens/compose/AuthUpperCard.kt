package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R


@Composable fun AuthUpperCard(heading:String, subHeading:String)
{
    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.login_image),
            contentDescription = "Upper Image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color(0xAA000000),
                    )
                ))
        )
        Column(
            modifier = Modifier.fillMaxSize(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                heading,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                subHeading,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(30.dp))
        }

    }
}