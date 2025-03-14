package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun HeadingSubHeading(modifier: Modifier = Modifier,heading:String,subHeading:String) {
    Text(
        buildAnnotatedString {
            withStyle(style =  SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )) {
                append("$heading\t ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            ) {
                append(subHeading)
            }
        },
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()

    )
}