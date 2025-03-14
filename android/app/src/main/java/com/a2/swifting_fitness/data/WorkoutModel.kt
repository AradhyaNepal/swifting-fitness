package com.a2.swifting_fitness.data

import com.a2.swifting_fitness.R

data class WorkoutModel(val minutes: Int, val heading:String, val subHeading:String,val carbs:String,var image:Int,var video:String)

fun getWorkoutItems(): MutableList<WorkoutModel>{
    val list= mutableListOf<WorkoutModel>()
    list.add(WorkoutModel(minutes = 17, heading = "Squat", subHeading = "Strong Leg", carbs = "412", image = R.drawable.cr7, video = "https://www.youtube.com/watch?v=VNsd8Yo5zvc"))
    list.add(WorkoutModel(minutes = 17, heading = "Bicep", subHeading = "Strong Arm", carbs = "412", image = R.drawable.cr7, video = "https://www.youtube.com/watch?v=VNsd8Yo5zvc"))
    list.add(WorkoutModel(minutes = 17, heading = "Back", subHeading = "Strong Back", carbs = "412", image = R.drawable.cr7, video = "https://www.youtube.com/watch?v=VNsd8Yo5zvc"))
    list.add(WorkoutModel(minutes = 17, heading = "Chest", subHeading = "Strong Chest", carbs = "412", image = R.drawable.cr7, video = "https://www.youtube.com/watch?v=VNsd8Yo5zvc"))
   return list


}
