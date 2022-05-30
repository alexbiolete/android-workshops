package com.eim.kotlin.workout.planner.ui.workouts.workout

import java.sql.Timestamp
import java.util.*

data class Workout(
    val title: String,
//    val date: Timestamp,
//    val userId: Number,
    var isDone: Boolean = false
) {
}