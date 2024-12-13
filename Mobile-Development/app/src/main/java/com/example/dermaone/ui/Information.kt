package com.example.dermaone.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dermaone.databinding.FragmentInformationBinding
import com.example.dermaone.ui.ui.adapter.HealthInformationAdapter
import com.example.dermaone.ui.ui.api.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Information : Fragment() {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHealthInformation.layoutManager = LinearLayoutManager(requireContext())

        fetchHealthInformation("penyakit kulit")
    }

    private fun fetchHealthInformation(query: String) {

        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiConfig.getNewsApiService().getHealthInformation(query)
                }

                if (isAdded) {
                    val adapter = HealthInformationAdapter(response.organicResults)
                    binding.rvHealthInformation.adapter = adapter
                }
            } catch (e: Exception) {
                Log.e("InformationFragment", "Error fetching health information", e)
                if (isAdded) {
                    Toast.makeText(requireContext(), "Failed to load data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } finally {
                if (isAdded) {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("InformationFragment", "View destroyed")
        _binding = null
    }
}
