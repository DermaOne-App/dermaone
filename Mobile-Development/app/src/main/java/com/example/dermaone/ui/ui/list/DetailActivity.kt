package com.example.dermaone.ui.ui.list

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dermaone.databinding.ActivityDetailBinding
import com.example.dermaone.R

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val name = intent.getStringExtra("name")
        val reason = intent.getStringExtra("reason")
        val symptom = intent.getStringExtra("symptom")
        val imageResourceId = intent.getIntExtra("imageResourceId", 0)

        val nameTextView = findViewById<TextView>(R.id.detailName)
        val reasonTextView = findViewById<TextView>(R.id.detailPenyebab)
        val symptomTextView = findViewById<TextView>(R.id.detailgejala)
        val imageView = findViewById<ImageView>(R.id.detailImage)

        nameTextView.text = name
        reasonTextView.text = reason
        symptomTextView.text = symptom
        imageView.setImageResource(imageResourceId)

    }
}