package com.eim.kotlin.workout.planner.ui.workouts.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eim.kotlin.workout.planner.R
import com.eim.kotlin.workout.planner.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val exercises: MutableList<Exercise>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var _binding: ItemExerciseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvExercise : TextView = itemView.findViewById(R.id.tvExercise)
        val cbExercise : CheckBox = itemView.findViewById(R.id.cbExercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_exercise,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exercises[position]
        holder.tvExercise.text = currentExercise.title
        holder.cbExercise.isChecked = currentExercise.isDone
        holder.cbExercise.setOnCheckedChangeListener { _, isChecked ->
            currentExercise.isDone = !currentExercise.isDone
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    fun addExercise(exercise: Exercise) {
        exercises.add(exercise)
        notifyItemInserted(exercises.size - 1)
    }
}