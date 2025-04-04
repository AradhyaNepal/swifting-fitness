package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R

@Composable
fun CustomButton(modifier: Modifier = Modifier,title:String,disabled: Boolean=false,color: Color=Color(0xFFF77500),onClick:()->Unit) {
    Button(modifier = modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors().copy(
        containerColor = color,
        contentColor = Color(0xFFFFFFFF),
        disabledContainerColor = Color(0xAAF77500)
    ), onClick = onClick, enabled = !disabled
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text =title,
                color = if(disabled)Color(0xFF000000)else Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.size(7.5.dp))
            Image(
                painter = painterResource(R.drawable.monotone_arrow_right),
                contentDescription = "Arrow",
            )
        }

    }
}