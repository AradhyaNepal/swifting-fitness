package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.screens.NextPreviousButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
private fun Preview() {
    HomeUpperCard()
}
@Composable
fun HomeUpperCard() {
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())

    Column {
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
        Spacer(Modifier.size(5.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(R.drawable.cr7),
                contentDescription = "Profile",
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.size(5.dp))
            Column (Modifier.weight(1f)){
                Text(
                    text = "Hello, Cristiano!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                    )
                Spacer(Modifier.size(15.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(R.drawable.health),
                        contentDescription = "Healthy",
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(Modifier.size(7.5.dp))
                    Text(
                        text = "88% Healthy",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(Modifier.size(12.5.dp))
                    Image(
                        painter = painterResource(R.drawable.star),
                        contentDescription = "Tier",
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(Modifier.size(7.5.dp))
                    Text(
                        text = "Pro",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Spacer(Modifier.size(5.dp))
            Image(
                painter = painterResource(R.drawable.front_arrow),
                contentDescription = "Go to Profile"
            )
        }
    }


}