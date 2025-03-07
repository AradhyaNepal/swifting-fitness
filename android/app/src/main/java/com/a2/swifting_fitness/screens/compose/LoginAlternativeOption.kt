package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun LoginAlternativeOptions(
    goToRegisterScreen: () -> Unit,
    goToForgetPasswordScreen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.White)
        Text(
            text = "or",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.White)
    }
    Spacer(Modifier.size(20.dp))
    SocialLogin()
    Spacer(Modifier.size(20.dp))
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Don't have an account? ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFF77500),
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Register")
            }
        },
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                goToRegisterScreen()
            }
    )
    Spacer(Modifier.size(20.dp))
    Text(
        modifier = Modifier.clickable {
            goToForgetPasswordScreen()
        }.fillMaxWidth(),
        text = "Forgot Password",
        textAlign = TextAlign.Center,
        color = Color(0xFFF77500),
        fontWeight = FontWeight.SemiBold,
        textDecoration = TextDecoration.Underline,
    )

}
