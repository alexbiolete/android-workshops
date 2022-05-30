package com.eim.kotlin.workout.planner.ui.workouts.workout

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eim.kotlin.workout.planner.ExercisesActivity
import com.eim.kotlin.workout.planner.R
import com.eim.kotlin.workout.planner.TimerActivity
import com.eim.kotlin.workout.planner.databinding.ItemWorkoutBinding
import com.google.firebase.firestore.FirebaseFirestore

class WorkoutAdapter(
    private val workouts: MutableList<Workout>,
    private val context: Context
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private var _binding: ItemWorkoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    private val workouts: MutableList<Workout> = mutableListOf()

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

    private fun saveWorkoutToFirestore(workout: Workout) {
        val db = FirebaseFirestore.getInstance()
        db.collection("workouts")
            .add(workout)
            .addOnSuccessListener {
                Toast.makeText(context, "Workout added successfully.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to add workout.", Toast.LENGTH_SHORT).show()
            }
    }

    fun addWorkout(workout: Workout) {
        workouts.add(workout)
        saveWorkoutToFirestore(workout)
        notifyItemInserted(workouts.size - 1)
    }
}