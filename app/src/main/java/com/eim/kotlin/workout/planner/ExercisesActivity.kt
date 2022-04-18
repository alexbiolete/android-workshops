package com.eim.kotlin.workout.planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eim.kotlin.workout.planner.databinding.ActivityExercisesBinding
import com.eim.kotlin.workout.planner.ui.objectives.objective.Objective
import com.eim.kotlin.workout.planner.ui.objectives.objective.ObjectiveAdapter
import java.util.*

class ExercisesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExercisesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        val objective1 = Objective(title = "Improve Deadlift PR", false)
//
//        exerciseAdapter = ObjectiveAdapter(mutableListOf(objective1))
//
//        binding.rvExercises.adapter = exerciseAdapter
//        binding.rvExercises.layoutManager = LinearLayoutManager(this.context)
//
//        binding.btnAddExercise.setOnClickListener {
//            val objectiveTitle = binding.etAddExercise.text.toString()
//            if (objectiveTitle.isNotEmpty()) {
//                val objective = Objective(objectiveTitle)
//                exerciseAdapter.addObjective(objective)
//                binding.etAddExercise.text.clear()
//            }
//        }
    }
}