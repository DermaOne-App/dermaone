package com.example.dermaone.ui.ui.virus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dermaone.R
import com.example.dermaone.databinding.ActivityListVirusBinding

class ListVirus : AppCompatActivity() {
    private lateinit var binding: ActivityListVirusBinding
    private lateinit var virusList: List<Virus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVirusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        virusList = listOf(
            Virus("BA - BAKTERI", getString(R.string.penjelasan_BA_Bakteri), getString(R.string.jenis_infeksi_BA_Bakteri), R.drawable.bakteri),
            Virus("FU - FUNGUS", getString(R.string.penjelasan_FU_FUNGUS), getString(R.string.jenis_infeksi_FU_FUNGUS), R.drawable.jamur),
            Virus("PA - PARASIT", getString(R.string.penjelasan_PA_PARASIT), getString(R.string.jenis_infeksi_PA_PARASIT),R.drawable.parasit),
            Virus("VI - VIRUS", getString(R.string.penjelasan_VI_VIRUS), getString(R.string.jenis_infeksi_VI_VIRUS), R.drawable.virus)
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = VirusAdapter(virusList) { selectedVirus ->
            val intent = Intent(this, DetailVirusActivity::class.java).apply {
                putExtra("name", selectedVirus.name)
                putExtra("explanation", selectedVirus.explanation)
                putExtra("type", selectedVirus.type)
                putExtra("imageResourceId", selectedVirus.imageResourceId)
            }
            startActivity(intent)
        }
    }
}