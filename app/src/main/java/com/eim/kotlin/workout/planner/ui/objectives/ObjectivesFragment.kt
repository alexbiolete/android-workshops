package com.eim.kotlin.workout.planner.ui.objectives

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eim.kotlin.workout.planner.databinding.FragmentObjectivesBinding
import com.eim.kotlin.workout.planner.ui.objectives.objective.Objective
import com.eim.kotlin.workout.planner.ui.objectives.objective.ObjectiveAdapter
import com.eim.kotlin.workout.planner.ui.workouts.workout.Workout
import com.google.firebase.firestore.FirebaseFirestore

class ObjectivesFragment : Fragment() {
    private lateinit var objectiveAdapter: ObjectiveAdapter

    private var _binding: FragmentObjectivesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
                ViewModelProvider(this).get(ObjectivesViewModel::class.java)

        _binding = FragmentObjectivesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val objective1 = Objective(title = "Improve Deadlift PR", false)

        val objectives: MutableList<Objective> = mutableListOf();

        val db = FirebaseFirestore.getInstance()
        val query = db.collection("objectives")
        val listener = query.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            for (objective in value!!) {
                objectives.add(
                    Objective(
                        title = objective.data.getValue("title").toString()
                    )
                )
            }
            Log.d(ContentValues.TAG, "Objectives: $objectives")
        }

        objectiveAdapter = ObjectiveAdapter(objectives, this.requireContext())

        binding.rvObjectives.adapter = objectiveAdapter
        binding.rvObjectives.layoutManager = LinearLayoutManager(this.context)

        binding.btnAddObjective.setOnClickListener {
            val objectiveTitle = binding.etAddObjective.text.toString()
            if (objectiveTitle.isNotEmpty()) {
                val objective = Objective(objectiveTitle)
                objectiveAdapter.addObjective(objective)
                binding.etAddObjective.text.clear()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}