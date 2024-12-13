package com.example.dermaone.ui.ui.virus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dermaone.R
import com.example.dermaone.databinding.ItemDiseasesBinding

class VirusAdapter(
    private val virus: List<Virus>,
    private val onClick: (Virus) -> Unit
) : RecyclerView.Adapter<VirusAdapter.DiseaseViewHolder>() {

    inner class DiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDiseasesBinding.bind(view)

        fun bind(virus: Virus) {
            binding.tvListName.text = virus.name
            binding.tvListDescription.text = virus.explanation
            binding.imgListPhoto.setImageResource(virus.imageResourceId)
            binding.cardView.setOnClickListener { onClick(virus) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diseases, parent, false)
        return DiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        holder.bind(virus[position])
    }

    override fun getItemCount(): Int = virus.size
}