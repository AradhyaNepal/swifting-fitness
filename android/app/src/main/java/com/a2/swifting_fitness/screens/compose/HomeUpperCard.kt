package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.screens.NextPreviousButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeUpperCard(modifier: Modifier = Modifier) {
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {

        }
    ) {
        Image(
            painter = painterResource(R.drawable.calendar),
            contentDescription = "Calendar",
            modifier = Modifier.size(25.dp)
        )
        Spacer(Modifier.size(20.dp))
        Text(
            dateFormat,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(Modifier.size(10.dp))
        NextPreviousButton(
            resources = R.drawable.notification_bell,
            onPressed = { },
            description = "Previous Screen",
            modifier = Modifier
                .height(40.dp)
                .width(40.dp),
            padding = 7.5.dp
        )
    }
}