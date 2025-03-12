package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutCard(modifier: Modifier = Modifier) {
    Column {
        HeadingSubHeading(heading = "Workouts", subHeading = "(25)")
        Spacer(Modifier.size(25.dp))
        LazyRow {
            items(10) {
                WorkoutCardItem()
            }
        }
    }
}

