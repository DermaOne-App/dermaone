package com.example.dermaone.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dermaone.databinding.FragmentHistoryBinding
import com.example.dermaone.ui.ui.adapter.HistoryAdapter

class History : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayPredictionHistory()
    }

    private fun displayPredictionHistory() {
        val sharedPreferences = requireContext().getSharedPreferences("PredictionHistory", Context.MODE_PRIVATE)
        val allEntries = sharedPreferences.all
        val historyList = mutableListOf<Pair<String, String>>()

        for ((_, value) in allEntries) {
            val parts = value.toString().split("|")
            if (parts.size == 2) {
                val result = parts[0]
                val imageUrl = parts[1]
                historyList.add(Pair(result, imageUrl))
            }
        }

        if (historyList.isNotEmpty()) {
            binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
            binding.rvHistory.adapter = HistoryAdapter(historyList)
        } else {
            binding.tvEmptyHistory.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
