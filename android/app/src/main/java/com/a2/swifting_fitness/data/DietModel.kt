package com.a2.swifting_fitness.data

import com.a2.swifting_fitness.R

data class DietModel(val protein: String, val heading:String, val carbs:String,var image:Int)

fun getDietItems(): MutableList<DietModel>{
    val list= mutableListOf<DietModel>()
    list.add(DietModel(protein = "10", heading = "Pizza", carbs = "1000", image = R.drawable.diet0))
    list.add(DietModel(protein = "10", heading = "Burger", carbs = "1000", image = R.drawable.diet1))
    list.add(DietModel(protein = "10", heading = "Pasta", carbs = "1000", image = R.drawable.diet3))
    list.add(DietModel(protein = "10", heading = "Chapati", carbs = "1000", image = R.drawable.diet4))
   return list


}
