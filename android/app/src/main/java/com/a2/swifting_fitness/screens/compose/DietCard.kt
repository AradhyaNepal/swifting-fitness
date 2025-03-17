package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.a2.swifting_fitness.data.getDietItems

@Preview
@Composable
private fun Preview() {
    DietCard(goToDetail = {})
}
@Composable
fun DietCard(goToDetail: (DietModel) -> Unit) {
    HeadingSubHeading(heading = "Diet & Nutrition", subHeading = "(5)", modifier = Modifier.padding(horizontal = 20.dp))
Spacer(Modifier.size(20.dp))
    LazyRow {
        items(getDietItems()) { item ->
           IndividualDiet(item, goToDetail )
        }
    }
}

@Composable
fun IndividualDiet(item: DietModel,goToDetail: (DietModel) -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                goToDetail(item)
            }
            .padding(horizontal = 15.dp)
            .border(
                width = 1.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .height(250.dp)
            .width(275.dp)
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = "Image of ${item.heading}",
            modifier=Modifier.fillMaxSize(),
            contentScale= ContentScale.Crop
        )
        Column (verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()){
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0x99000000))
                    .padding(5.dp),
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        "${item.protein}g",
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x88000000)),
            ){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Spacer(Modifier.size(10.dp))
                    Column {
                        Spacer(Modifier.size(5.dp))
                        Text(
                            item.heading,
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
                                "${item.carbs}kCal",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                        Spacer(Modifier.size(15.dp))
                    }
                    Spacer(Modifier.size(15.dp))
                    Spacer(Modifier.weight(1f))
                    Button (
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color(0xFFF77500),
                            contentColor = Color(0xFFFFFFFF),
                        ),
                        shape = CircleShape,
                        onClick = {
                            goToDetail(item)
                        }
                    ){
                        Image(
                            painter = painterResource(R.drawable.monotone_arrow_right),
                            contentDescription = "Arrow",
                        )
                    }
                    Spacer(Modifier.size(10.dp))


                }
            }

        }

    }
}

