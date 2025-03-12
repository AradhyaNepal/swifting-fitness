package com.a2.swifting_fitness.screens.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DietCard(modifier: Modifier = Modifier) {
    HeadingSubHeading(heading = "Diet & Nutrition", subHeading = "(5)")
    Spacer(Modifier.size(25.dp))
}