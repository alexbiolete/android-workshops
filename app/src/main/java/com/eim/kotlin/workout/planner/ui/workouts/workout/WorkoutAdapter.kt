package com.eim.kotlin.workout.planner.ui.workouts.workout

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.eim.kotlin.workout.planner.ExercisesActivity
import com.eim.kotlin.workout.planner.R
import com.eim.kotlin.workout.planner.TimerActivity
import com.eim.kotlin.workout.planner.databinding.ItemWorkoutBinding
import com.google.android.material.snackbar.Snackbar

class WorkoutAdapter(
    private val workouts: MutableList<Workout>
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private var _binding: ItemWorkoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TimerActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWorkout : TextView = itemView.findViewById(R.id.tvWorkout)
        val btnExercises : Button = itemView.findViewById(R.id.btnExercises)
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
        holder.btnExercises.setOnClickListener {
            val intent = Intent(holder.itemView.context, ExercisesActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    fun addWorkout(workout: Workout) {
        workouts.add(workout)
        notifyItemInserted(workouts.size - 1)
    }
}