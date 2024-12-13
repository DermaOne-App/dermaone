package com.example.dermaone.ui.ui.virus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dermaone.R
import com.example.dermaone.databinding.ActivityDetailVirusBinding

class DetailVirusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVirusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVirusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val name = intent.getStringExtra("name")
        val explanation = intent.getStringExtra("explanation")
        val type = intent.getStringExtra("type")
        val imageResourceId = intent.getIntExtra("imageResourceId", 0)

        val nameTextView = findViewById<TextView>(R.id.detailName)
        val explanationTextView = findViewById<TextView>(R.id.detailPenjelasan)
        val typeTextView = findViewById<TextView>(R.id.detailJenis)
        val imageView = findViewById<ImageView>(R.id.detailImage)

        nameTextView.text = name
        explanationTextView.text = explanation
        typeTextView.text = type
        imageView.setImageResource(imageResourceId)

    }
}