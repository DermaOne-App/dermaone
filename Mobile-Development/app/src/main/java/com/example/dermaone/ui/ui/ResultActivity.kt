package com.example.dermaone.ui.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dermaone.databinding.ActivityResultBinding
import com.example.dermaone.R

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val label = intent.getStringExtra("Disease_NAME") ?: "Unknown"
        val confidence = intent.getFloatExtra("CONFIDENCE", 0f)
        val imageUrl = intent.getStringExtra("IMAGE_URL")

        binding.tvDiseaseName.text = label
        binding.tvConfidence.text = String.format("Confidence: %.2f%%", confidence)

        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(binding.ivPredictionImage)
        } else {
            binding.ivPredictionImage.setImageResource(R.drawable.ic_placeholder_image)
        }

        savePredictionResult(label, confidence, imageUrl)
    }

    private fun savePredictionResult(diseaseName: String, confidence: Float, imageUrl: String?) {
        val sharedPreferences = getSharedPreferences("PredictionHistory", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val currentTime = System.currentTimeMillis().toString()

        val predictionData = "$diseaseName|$imageUrl"
        editor.putString(currentTime, predictionData)
        editor.apply()
    }
}
