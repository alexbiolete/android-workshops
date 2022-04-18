package com.eim.kotlin.workout.planner.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eim.kotlin.workout.planner.databinding.FragmentWorkoutsBinding
import com.eim.kotlin.workout.planner.ui.workouts.workout.Workout
import com.eim.kotlin.workout.planner.ui.workouts.workout.WorkoutAdapter

class WorkoutsFragment : Fragment() {
    private lateinit var workoutAdapter: WorkoutAdapter

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

        workoutAdapter = WorkoutAdapter(mutableListOf())

        binding.rvWorkouts.adapter = workoutAdapter
        binding.rvWorkouts.layoutManager = LinearLayoutManager(this.context)

        binding.btnAddWorkout.setOnClickListener {
            val workoutTitle = binding.etAddWorkout.text.toString()
            if (workoutTitle.isNotEmpty()) {
                val exercise = Workout(workoutTitle)
                workoutAdapter.addWorkout(exercise)
                binding.etAddWorkout.text.clear()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}