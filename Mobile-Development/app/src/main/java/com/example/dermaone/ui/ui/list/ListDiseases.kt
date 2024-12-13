package com.example.dermaone.ui.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dermaone.R
import com.example.dermaone.databinding.ActivityListDiseasesBinding

class ListDiseases : AppCompatActivity() {
    private lateinit var binding: ActivityListDiseasesBinding
    private lateinit var diseaseList: List<Disease>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDiseasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        diseaseList = listOf(
            Disease("Cellulitis", getString(R.string.penyebab_cellulitis), getString(R.string.gejala_cellulitis), R.drawable.cellulitis),
            Disease("Impetigo", getString(R.string.penyebab_impetigo), getString(R.string.gejala_impetigo), R.drawable.impetigo),
            Disease("Athlete Foot", getString(R.string.penyebab_athlete_foot), getString(R.string.gejala_athlete_foot), R.drawable.athlete_foot),
            Disease("Nail Fungus", getString(R.string.penyebab_jamur_kuku), getString(R.string.gejala_jamur_kuku), R.drawable.nail_fungus),
            Disease("Ringworm", getString(R.string.penyebab_kurap), getString(R.string.gejala_kurap), R.drawable.ringworm),
            Disease("Cutaneous Larva Migrans", getString(R.string.penyebab_CLM), getString(R.string.gejala_CLM), R.drawable.clm),
            Disease("Chickenpox", getString(R.string.penyebab_cacar_air), getString(R.string.Gejala_Cacar_Air), R.drawable.chickenpox),
            Disease("Shingles", getString(R.string.penyebab_herpes_zoster), getString(R.string.gejala_herpes_zoster), R.drawable.shingles)
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = DiseaseAdapter(diseaseList) { selectedDisease ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("name", selectedDisease.name)
                putExtra("reason", selectedDisease.reason)
                putExtra("symptom", selectedDisease.symptom)
                putExtra("imageResourceId", selectedDisease.imageResourceId)
            }
            startActivity(intent)
        }
    }
}
