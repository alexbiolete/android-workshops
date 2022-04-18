package com.eim.kotlin.workout.planner.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eim.kotlin.workout.planner.databinding.FragmentWorkoutsBinding
import com.eim.kotlin.workout.planner.ui.workouts.exercises.Exercise
import com.eim.kotlin.workout.planner.ui.workouts.exercises.ExerciseAdapter

class WorkoutsFragment : Fragment() {
    private lateinit var exerciseAdapter: ExerciseAdapter

    private var _binding: FragmentWorkoutsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val workoutsViewModel =
                ViewModelProvider(this).get(WorkoutsViewModel::class.java)

        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        exerciseAdapter = ExerciseAdapter(mutableListOf())

        binding.rvExercises.adapter = exerciseAdapter
        binding.rvExercises.layoutManager = LinearLayoutManager(this.context)

        binding.btnAddExercise.setOnClickListener {
            val exerciseTitle = binding.etAddExercise.text.toString()
            if (exerciseTitle.isNotEmpty()) {
                val exercise = Exercise(exerciseTitle)
                exerciseAdapter.addExercise(exercise)
                binding.etAddExercise.text.clear()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}