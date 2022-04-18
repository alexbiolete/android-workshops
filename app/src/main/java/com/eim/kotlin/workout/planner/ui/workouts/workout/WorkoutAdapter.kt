package com.eim.kotlin.workout.planner.ui.workouts.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eim.kotlin.workout.planner.R
import com.eim.kotlin.workout.planner.databinding.ItemWorkoutBinding

class WorkoutAdapter(
    private val workouts: MutableList<Workout>
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private var _binding: ItemWorkoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWorkout : TextView = itemView.findViewById(R.id.tvWorkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_workout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val currentWorkout = workouts[position]
        holder.tvWorkout.text = currentWorkout.title
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    fun addWorkout(workout: Workout) {
        workouts.add(workout)
        notifyItemInserted(workouts.size - 1)
    }
}