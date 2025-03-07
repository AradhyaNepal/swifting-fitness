package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R

@Composable
fun SocialLogin(modifier: Modifier = Modifier) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
    modifier.border(width = 1.dp, color = Color(0xFFF77500), shape = RoundedCornerShape(12.dp))
        .clickable {
            println("Google login pressed")
        }.padding(vertical = 17.5.dp).fillMaxWidth()
    ){
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.google),
            contentDescription = "Google Login",

        )
        Spacer(Modifier.size(10.dp))
        Text("Sign in with Google", color = Color.White)

    }

}