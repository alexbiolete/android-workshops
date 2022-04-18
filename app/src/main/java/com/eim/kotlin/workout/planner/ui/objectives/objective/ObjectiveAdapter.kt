package com.eim.kotlin.workout.planner.ui.objectives.objective

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eim.kotlin.workout.planner.R
import com.eim.kotlin.workout.planner.databinding.ItemObjectiveBinding

class ObjectiveAdapter(
    private val objectives: MutableList<Objective>
) : RecyclerView.Adapter<ObjectiveAdapter.ObjectiveViewHolder>() {

    private var _binding: ItemObjectiveBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    class ObjectiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvObjective : TextView = itemView.findViewById(R.id.tvObjective)
        val cbObjective : CheckBox = itemView.findViewById(R.id.cbObjective)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveViewHolder {
        return ObjectiveViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_objective,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ObjectiveViewHolder, position: Int) {
        val currentObjective = objectives[position]
        holder.tvObjective.text = currentObjective.title
        holder.cbObjective.isChecked = currentObjective.isDone
        holder.cbObjective.setOnCheckedChangeListener { _, isChecked ->
            currentObjective.isDone = !currentObjective.isDone
        }
    }

    override fun getItemCount(): Int {
        return objectives.size
    }

    fun addObjective(objective: Objective) {
        objectives.add(objective)
        notifyItemInserted(objectives.size - 1)
    }
}