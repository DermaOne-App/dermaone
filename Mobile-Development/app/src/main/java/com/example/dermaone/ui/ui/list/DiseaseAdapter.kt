package com.example.dermaone.ui.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dermaone.R
import com.example.dermaone.databinding.ItemDiseasesBinding

class DiseaseAdapter(
    private val diseases: List<Disease>,
    private val onClick: (Disease) -> Unit
) : RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder>() {

    inner class DiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDiseasesBinding.bind(view)

        fun bind(disease: Disease) {
            binding.tvListName.text = disease.name
            binding.tvListDescription.text = disease.symptom
            binding.imgListPhoto.setImageResource(disease.imageResourceId)
            binding.cardView.setOnClickListener { onClick(disease) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diseases, parent, false)
        return DiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        holder.bind(diseases[position])
    }

    override fun getItemCount(): Int = diseases.size
}
