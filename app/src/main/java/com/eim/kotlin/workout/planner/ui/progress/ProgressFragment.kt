package com.eim.kotlin.workout.planner.ui.progress

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eim.kotlin.workout.planner.*
import com.eim.kotlin.workout.planner.databinding.FragmentProgressBinding
import com.github.mikephil.charting.charts.RadarChart

class ProgressFragment : Fragment() {
    private var chart: RadarChart? = null

    private var _binding: FragmentProgressBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(ProgressViewModel::class.java)

        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        val root: View = binding.root

        chart = binding.rcProgress;

        binding.btnTimerActivity.setOnClickListener {
            val intent = Intent(this@ProgressFragment.requireContext(), TimerActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}