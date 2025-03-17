package com.a2.swifting_fitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2.swifting_fitness.R
import com.a2.swifting_fitness.data.DietModel
import com.a2.swifting_fitness.screens.compose.CustomButton

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    DietDetailScreen(dietModel = DietModel(protein = "10", carbs = "10",  heading = "Pizza", image = R.drawable.diet4))
}
@Composable
fun DietDetailScreen(modifier: Modifier = Modifier,dietModel: DietModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(dietModel.image),
            contentDescription = "Image of ${dietModel.heading}",
            modifier=Modifier.fillMaxSize(),
            contentScale= ContentScale.Crop
        )
        Column (verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()){
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0x99000000))
                    .padding(5.dp),
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        "${dietModel.protein}g",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(Modifier.size(15.dp))
                    Text(
                        "Protein",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
            Column {
                Row {
                    Spacer(Modifier.size(10.dp))
                    CustomButton(title = "Done", modifier = Modifier.weight(1f)) { }
                    Spacer(Modifier.size(20.dp))
                    CustomButton(title = "Failed", modifier = Modifier.weight(1f), color = Color.Red) { }
                    Spacer(Modifier.size(10.dp))
                }
                Spacer(Modifier.size(15.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x88000000)),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(Modifier.size(10.dp))
                        Column {
                            Spacer(Modifier.size(5.dp))
                            Text(
                                dietModel.heading,
                                fontSize = 25.sp,
                                color = Color.White
                            )
                            Spacer(Modifier.size(5.dp))
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.fire),
                                    contentDescription = "Calories",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.size(5.dp))
                                Text(
                                    "${dietModel.carbs}kCal",
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            }
                            Spacer(Modifier.size(15.dp))
                        }
                        Spacer(Modifier.size(15.dp))
                        Spacer(Modifier.weight(1f))
                        Spacer(Modifier.size(10.dp))


                    }
                }
            }

        }

    }
}